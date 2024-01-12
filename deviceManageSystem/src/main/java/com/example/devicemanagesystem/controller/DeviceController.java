package com.example.devicemanagesystem.controller;


import com.example.devicemanagesystem.pojo.Device;
import com.example.devicemanagesystem.pojo.Result;
import com.example.devicemanagesystem.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@RestController
@RequestMapping("/device")
public class DeviceController {
    @Autowired
    DeviceService deviceService;

    /**
     * 添加设备
     * @param device Device
     * @return result
     */
    @PostMapping("/add")
    public Result addDevice(@RequestBody Device device){
        return deviceService.add(device);
    }

    /**
     * 根据设备号删除设备
     * @param deviceId
     * @return result
     */
    @DeleteMapping("/delete")
    public Result deleteDevice(Integer deviceId){
        return deviceService.delete(deviceId);
    }

    /**
     * 更新设备信息
     * @param device
     * @return result
     */
    @PutMapping("/update")
    public Result updateDevice(@RequestBody Device device){
        return deviceService.update(device);
    }

    /**
     * 查询单个设备
     * @param deviceId
     * @return result
     */
    @GetMapping(value = "/{deviceId}")
    public Result selectDevice(@PathVariable Integer deviceId){
        return deviceService.select(deviceId);
    }

    /**
     * 条件查询多个设备
     * @return result
     */
    @GetMapping("/list")
    public Result List(Device device){
        return deviceService.list(device);
    }


}
