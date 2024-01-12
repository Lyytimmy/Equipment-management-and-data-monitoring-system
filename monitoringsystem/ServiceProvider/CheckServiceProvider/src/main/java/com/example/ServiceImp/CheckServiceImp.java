package com.example.ServiceImp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.CheckService;
import com.example.EmailService;
import com.example.Pojo.Record;
import com.example.Pojo.Result;
import com.example.mapper.CheckMapper;
import com.example.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service(interfaceClass = CheckService.class)
public class CheckServiceImp implements CheckService{
    //private static final Logger logger = LoggerFactory.getLogger(CheckServiceImp.class);
    @Autowired
    CheckMapper checkMapper;
    Tool tool = new Tool();
    List<String> tableNames = tool.getAllTableName();

    @Reference
    private EmailService emailService;


    @Override
    public Result list(Record record) {
        Map<String , Object> res = new HashMap<>();

        List<Record> record1 = checkMapper.list1(record);
        res.put("step1", record1);
        List<Record> record2 = checkMapper.list2(record);
        res.put("step2", record2);

        return new Result(0, "查询成功", res);
    }

}
