package com.example.api;

import com.example.Pojo.Record;
import com.example.Pojo.Result;
import com.example.RecordService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
@RestController
public class A_api {
    @Reference
    private RecordService recordService;

    @PostMapping("/record")
    public Result recordApi(@RequestBody Record record) {
        System.out.println(record);
        return recordService.addRecord(record);
    }
}
