package com.example.Pojo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record implements Serializable {
    Integer componentId;
    Integer stepId;
    Integer workstationId;
    Integer componentType;
    Date date;
    Time time;
    Integer state;
    boolean isDeleted;
}
