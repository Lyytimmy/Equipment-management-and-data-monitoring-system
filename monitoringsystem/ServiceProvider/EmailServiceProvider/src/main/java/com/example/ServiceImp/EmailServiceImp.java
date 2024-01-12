package com.example.ServiceImp;


import com.alibaba.dubbo.config.annotation.Service;
import com.example.EmailService;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;


@Service(interfaceClass = EmailService.class)
public class EmailServiceImp implements EmailService{

    @Autowired
    private JavaMailSender mailSender;



    @Override
    @Async
    public void sendEmail(String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("2500673659@qq.com"); // 指定发件人地址
        message.setTo("2500673659@qq.com"); // 收件人地址
        message.setSubject("Warning!"); // 邮件主题
        message.setText(text); // 邮件正文
        mailSender.send(message); // 发送邮件
    }

}
