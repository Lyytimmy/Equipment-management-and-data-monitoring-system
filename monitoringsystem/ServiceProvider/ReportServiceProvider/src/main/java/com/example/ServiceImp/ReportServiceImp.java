package com.example.ServiceImp;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.example.EmailService;
import com.example.Pojo.Record;
import com.example.Pojo.Result;
import com.example.ReportService;
import com.example.mapper.ReportMapper;
import com.example.tool.Tool;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service(interfaceClass = ReportService.class)
public class ReportServiceImp implements ReportService {
    //private static final Logger logger = LoggerFactory.getLogger(ReportServiceImp.class);

    @Autowired
    private ReportMapper reportMapper;

    @Reference
    private EmailService emailService;

    Tool tool = new Tool();
    List<String> tableNames = tool.getAllTableName();

    @Override
    public Result report(Integer componentId) {
        boolean needWarn = false;
        String fileName = "Report" + componentId;
        if (tableNames.isEmpty()) {
            log.info("获取数据库表失败,请注意");
            return new Result(-1, "获取数据库表失败");
        }

        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);
            try (PDPageContentStream contentStream = new PDPageContentStream(document, page)) {
                contentStream.beginText();
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);
                // 基本信息 componentId， componentType
                contentStream.newLineAtOffset(25, 750);
                contentStream.showText("Component ID: " + componentId);
                contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                Record record1 = reportMapper.checkById1(componentId);
                if (record1!=null){
                    contentStream.showText("step1");
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("Workstation ID: " + record1.getWorkstationId());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("Date: " + record1.getDate());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("Time: " + record1.getTime());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("State: " + record1.getState());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    if (record1.getState() == 3){
                        needWarn = true;
                    }
                }
                Record record2 = reportMapper.checkById2(componentId);
                if (record2!=null){
                    contentStream.showText("step2");
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("Workstation ID: " + record2.getWorkstationId());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("Date: " + record2.getDate());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("Time: " + record2.getTime());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.showText("State: " + record2.getState());
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    contentStream.newLineAtOffset(0, -12); // 下一行的Y坐标位置
                    if (record2.getState() == 3){
                        needWarn = true;
                    }
                }
                contentStream.endText();
            }

            document.save(tool.getPDFPath() + fileName + ".pdf");
            log.info("生成组件{}的报告", componentId);
            if(needWarn){
                System.out.println("error");
                if (emailService!=null){
                    System.out.println("111");
                    emailService.sendEmail("已生成错误报告，请检查"); // 异步发送邮箱，防止被阻塞
                }
            }

        } catch (IOException e) {

            throw new RuntimeException(e);

        }

        return new Result(0, "以生成报告在指定地址:" + tool.getPDFPath() + fileName);
    }
}
