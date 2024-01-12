package com.example;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableDubbo
@EnableScheduling
@MapperScan("com.example.mapper")
public class RecordMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecordMainApplication.class, args);
    }

}
