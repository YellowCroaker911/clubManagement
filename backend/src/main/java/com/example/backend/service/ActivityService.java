package com.example.backend.service;

import com.example.backend.utils.result.ResultData;

import java.util.Date;

public interface ActivityService {
    ResultData<Object> activityRelease(String clubId,String name, String info, String title, Date beginTime, Date endTime,Date signBeginTime, Date signEndTime, String address, String sign,String money);
    ResultData<Object> activityAlterInfo(String id,String name, String info, String title, Date beginTime, Date endTime,Date signBeginTime, Date signEndTime, String address, String sign,String money,String summary);
    ResultData<Object> activityDelete(String id);
}
