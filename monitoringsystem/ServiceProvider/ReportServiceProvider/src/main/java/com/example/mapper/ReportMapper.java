package com.example.mapper;

import com.example.Pojo.Record;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ReportMapper {
    Record checkById1(Integer componentId);
    Record checkById2(Integer componentId);
}
