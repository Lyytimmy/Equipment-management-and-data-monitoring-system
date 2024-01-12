package com.example;

import com.example.Pojo.Result;

public interface ReportService {
    /**
     * 根据部件Id生成包含部件编号、检测项目、检测结果、检测时间等信息的质量检测报告
     * @param componentId
     * @return result
     * 生产一个csv文件
     */
    public Result report(Integer componentId);
}
