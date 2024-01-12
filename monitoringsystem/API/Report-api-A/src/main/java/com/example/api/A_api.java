package com.example.api;

import com.alibaba.dubbo.config.annotation.Reference;
import com.example.Pojo.Result;
import com.example.ReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/report")
public class A_api {
    @Reference
    private ReportService reportService;

    @GetMapping(value = "/{componentId}")
    public Result checkByIdApi(@PathVariable Integer componentId){
        return reportService.report(componentId);
    }
}
