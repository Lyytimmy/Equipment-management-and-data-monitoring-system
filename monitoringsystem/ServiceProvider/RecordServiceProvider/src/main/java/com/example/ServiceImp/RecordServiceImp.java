package com.example.ServiceImp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.EmailService;
import com.example.Pojo.Record;
import com.example.Pojo.Result;
import com.example.RecordService;
import com.example.mapper.RecordMapper;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.tool.Tool;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Service(interfaceClass = RecordService.class)
public class RecordServiceImp implements RecordService{

    private final BlockingQueue<Record> buffer = new LinkedBlockingQueue<>();


    Tool tool = new Tool(); // 在common模块下的工具类，有一些公共参数和方法

    @Autowired
    RecordMapper recordMapper;

    @Reference
    private EmailService emailService;

    @Override
    public Result addRecord(Record record) {
        System.out.println(buffer.size());
        if(buffer.add(record)){
            if (buffer.size() >= tool.getBatchSize()) {
                return tryProcessBatch();
            }
            log.info("{}添加到缓冲区",record);
            return new Result(0, "记录已添加到缓冲区");
        }
        log.info("栈空间已满，{}未被存入",record);
        return new Result(-1, "栈空间异常，请处理");
    }


    @Override
    public Result tryProcessBatch() {
        if (!buffer.isEmpty()){
            try {
                List<Record> batch = new ArrayList<>();
                buffer.drainTo(batch);
                return saveAll(batch);
            } catch (Exception e) {
                log.error("批量处理失败", e);
                return new Result(-1, "批量处理失败: " + e.getMessage());
            }
        }
        return new Result(-1,"缓冲区为空");
    }


    @Override
    @Scheduled(fixedDelay = 600000)
    public void processBatch() {
        if (!buffer.isEmpty()) {
            List<Record> batch = new ArrayList<>();
            buffer.drainTo(batch);
            try {
                saveAll(batch);
            } catch (Exception e) {
                log.error("定时批量处理失败", e);
            }
        }
    }


    @Override
    @Transactional
    public Result saveAll(List<Record> records) {
        try {
            List<Record> validRecords = new ArrayList<>();
            List<Record> validRecords1 = new ArrayList<>();
            List<Record> validRecords2 = new ArrayList<>();
            List<String> missingTables = new ArrayList<>();

            // 1. 筛选出存在对应表的记录
            for (Record record : records) {
                String stepName = "step" + record.getStepId();
                boolean res = recordMapper.isTableExist(stepName);
                if (!res) {
                    missingTables.add(stepName);
                    log.info("缺少数据库表: {}", stepName);
                    emailService.sendEmail("缺少数据库表请处理");
                } else if (record.getStepId()==1){
                    validRecords.add(record);
                    validRecords1.add(record);
                } else if (record.getStepId()==2){
                    validRecords.add(record);
                    validRecords2.add(record);
                }
            }

            // 如果所有表都不存在
            if (validRecords.isEmpty()) {
                log.info("批处理失败！请注意");
                emailService.sendEmail("批处理失败，请处理");
                return new Result(-1, "所有记录的对应表都不存在: " + missingTables);
            }
            // 2. 处理每个有效的记录
            for (Record record : validRecords) {
                String stateString = tool.getString(record.getState());
                if ("缺陷".equals(stateString)) {
                    String text = record.getComponentId() + "在步骤" + record.getStepId() +"产生缺陷,请处理";
                    emailService.sendEmail(text);
                    log.info("{}在步骤{}产生缺陷，请注意", record.getComponentId(), record.getStepId());
                }
            }


            // 3. 批量插入数据1 和 2
            int resRecord1 = 0;
            int resRecord2 = 0;
            if (!validRecords1.isEmpty()){
                resRecord1 = recordMapper.insertRecords1(validRecords1);
            }else {
                resRecord1 = 1;
            }
            if (!validRecords2.isEmpty()){
                resRecord2 = recordMapper.insertRecords2(validRecords2);
            }else {
                resRecord2 = 1;
            }

            if (resRecord1 != 0 && resRecord2 !=0) {
                log.info("成功存储记录");
                return new Result(0, "记录成功");
            } else {
                log.info("存储记录失败");
                return new Result(-1, "记录失败");
            }
        } catch (Exception e) {
            emailService.sendEmail("批处理失败，请处理");
            log.error("批量存储过程中出现错误，事务将回滚: ", e);
            throw e;
        }
    }

}
