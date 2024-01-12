package com.example.mapper;

import com.example.Pojo.Record;
import org.apache.ibatis.annotations.Mapper;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Mapper
public interface CheckMapper {
    List<Record> list1(Record record);
    List<Record> list2(Record record);

}
