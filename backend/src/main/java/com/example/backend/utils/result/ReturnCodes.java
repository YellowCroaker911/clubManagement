package com.example.backend.utils.result;

import java.util.HashMap;
import java.util.Map;

public class ReturnCodes {

    public static Map<Integer,String> returnCodes = new HashMap<>();
    static{
        returnCodes.put(100,"成功");
        returnCodes.put(201,"用户名不能为空");
        returnCodes.put(202,"密码不能为空");
        returnCodes.put(203,"用户名长度不能大于20");
        returnCodes.put(204,"密码长度不能大于20");
        returnCodes.put(205,"两次输入的密码不一致");
        returnCodes.put(206,"用户名已存在");
    }
}
