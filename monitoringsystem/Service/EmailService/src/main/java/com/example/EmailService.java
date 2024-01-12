package com.example;

import org.apache.pdfbox.pdmodel.PDDocument;

public interface EmailService {
    /**
     * 发送邮件
     */
    void sendEmail(String text);
}
