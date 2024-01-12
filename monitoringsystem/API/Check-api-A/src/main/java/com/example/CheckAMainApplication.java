package com.example;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubboConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubboConfig
public class CheckAMainApplication {
    public static void main(String[] args) {
        SpringApplication.run(CheckAMainApplication.class, args);
    }
}
