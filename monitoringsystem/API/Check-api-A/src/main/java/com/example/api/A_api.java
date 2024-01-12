package com.example.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.CheckService;
import com.example.Pojo.Record;
import com.example.Pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Slf4j
@RestController
public class A_api {
    @Reference
    private CheckService checkService;

    @GetMapping("/check")
    public Result checkApi(Record record){
        return checkService.list(record);
    }


}
