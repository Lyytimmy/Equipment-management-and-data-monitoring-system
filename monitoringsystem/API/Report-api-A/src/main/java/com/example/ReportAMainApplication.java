package com.example;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfig
public class ReportAMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReportAMainApplication.class, args);
    }
}
