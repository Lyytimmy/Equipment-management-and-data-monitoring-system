package com.example.devicemanagesystem.mapper;

import com.example.devicemanagesystem.pojo.Device;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Mapper
public interface DeviceMapper {

    public int add(Device device);
    public int deleteById(Integer deviceId);
    public int update(Device device);
    public Device selectById(Integer deviceId);
    public List<Device> list(Device device);
}
