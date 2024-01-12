package com.example.devicemanagesystem.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Device {
    private Integer deviceId;
    private String workplaceName;
    private String providerName;
    private String state;
    private boolean isDelete;
}
