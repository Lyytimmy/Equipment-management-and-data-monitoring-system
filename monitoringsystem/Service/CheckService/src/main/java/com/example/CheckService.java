package com.example;
import com.example.Pojo.Record;
import com.example.Pojo.Result;
public interface CheckService {
    /**
     * 根据时间、部件类型、工作站编号等条件查询缺陷报告的详细信息
     * @param record
     * @return Result
     */
    Result list(Record record);

}
