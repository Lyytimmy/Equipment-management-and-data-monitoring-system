package com.example.tool;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Tool {

    public String getString(Integer state) {
        String res = "";
        switch (state){
            case 1:
                res = "进行中";
                break;
            case 2:
                res = "完成了";
                break;
            case 3:
                res = "缺陷";
                break;
            default:
                res = "其他";
        }
        return res;
    }
    public List<String> getAllTableName(){
        List<String> alltableName = new ArrayList<String>();
        alltableName.add("step1");
        alltableName.add("step2");
        return alltableName;
    }

    String userHome = System.getProperty("user.home");
    String PDFPath = userHome + "\\Desktop\\";
    int batchSize = 3;
}
