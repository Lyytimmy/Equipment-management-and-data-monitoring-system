package com.example.devicemanagesystem.service.Imp;

import com.example.devicemanagesystem.mapper.DeviceMapper;
import com.example.devicemanagesystem.pojo.Device;
import com.example.devicemanagesystem.pojo.Result;
import com.example.devicemanagesystem.service.DeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeviceServiceImp implements DeviceService {
    @Autowired
    DeviceMapper deviceMapper;

    @Override
    public Result add(Device device) {
        int res = deviceMapper.add(device);
        if (res != 0){
            return new Result(0,"插入成功");
        }
        return new Result(-1, "插入失败");
    }

    @Override
    public Result delete(Integer deviceId) {
        int res = deviceMapper.deleteById(deviceId);
        Device isDelete = deviceMapper.selectById(deviceId);
        if (res != 0 && isDelete == null){
            return new Result(0,"删除成功");
        }
        return new Result(-1, "删除失败");
    }

    @Override
    public Result update(Device device) {
        int res = deviceMapper.update(device);
        if (res != 0){
            return new Result(0,"更新成功");
        }
        return new Result(-1, "更新失败");
    }

    @Override
    public Result select(Integer deviceId) {
        Device res = deviceMapper.selectById(deviceId);
        if (res != null){
            return new Result(0, "查询成功", res);
        }
        return new Result(-1, "查询失败");
    }

    @Override
    public Result list(Device device) {
        List<Device> allDevice = new ArrayList<>();
        allDevice = deviceMapper.list(device);
        if(!allDevice.isEmpty()){
            return new Result(0, "查询成功", allDevice);
        }
        return  new Result(-1, "查询失败");
    }
}
