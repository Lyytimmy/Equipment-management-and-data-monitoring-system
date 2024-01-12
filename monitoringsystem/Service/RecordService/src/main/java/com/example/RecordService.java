package com.example;
import com.example.Pojo.Record;
import com.example.Pojo.Result;

import java.util.AbstractMap;
import java.util.List;

public interface RecordService {
    /**
     * 记录信息服务
     * @param record
     * return Result
     */

    public Result addRecord(Record record);

    public void processBatch();

    public Result tryProcessBatch();

    public Result saveAll(List<Record> records);
}
