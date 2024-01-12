package com.example.mapper;

import com.example.Pojo.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecordMapper {
    public boolean isTableExist(String stepName);
    public int insertRecords1(List<Record> list);
    public int insertRecords2(List<Record> list);
}
