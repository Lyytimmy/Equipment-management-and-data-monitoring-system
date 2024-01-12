package com.example.devicemanagesystem.service;

import com.example.devicemanagesystem.pojo.Device;
import com.example.devicemanagesystem.pojo.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

public interface DeviceService {

    public Result add(Device device);
    public Result delete(Integer deviceId);
    public Result update(Device device);
    public Result select(Integer deviceId);
    public Result list(Device device);
}
