package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.Club;
import com.example.backend.service.ActivityService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class ActivityServiceImpl implements ActivityService {
    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ClubMapper clubMapper;

    @Override
    public ResultData<Object> activityRelease(String clubId,String name, String info, String title, Date beginTime, Date endTime,Date signBeginTime, Date signEndTime, String address, String sign,String money) {

        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",clubId);
        Club club = clubMapper.selectOne(queryWrapper);
        if(club == null){
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        Activity activity = new Activity();
        activity.setClubId(parseLong(clubId));
        activity.setName(name);
        activity.setInfo(info);
        activity.setTitle(title);
        activity.setBeginTime(beginTime);
        activity.setSignBeginTime(signBeginTime);
        activity.setSignEndTime(signEndTime);
        activity.setEndTime(endTime);
        activity.setAddress(address);
        activity.setSign(sign);
        activity.setMoney(parseLong(money));

        activityMapper.insert(activity);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> activityAlterInfo(String id, String name, String info, String title, Date beginTime, Date endTime,Date signBeginTime, Date signEndTime, String address, String sign, String money, String summary) {

        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Activity activity = activityMapper.selectOne(queryWrapper);
        if (activity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        activity.setName(name);
        activity.setInfo(info);
        activity.setTitle(title);
        activity.setBeginTime(beginTime);
        activity.setEndTime(endTime);
        activity.setAddress(address);
        activity.setSignBeginTime(signBeginTime);
        activity.setSignEndTime(signEndTime);
        activity.setSign(sign);
        activity.setMoney(parseLong(money));
        activity.setSummary(summary);

        activityMapper.updateById(activity);
        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> activityDelete(String id){
        QueryWrapper<Activity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Activity activity = activityMapper.selectOne(queryWrapper);
        if (activity == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }
        activityMapper.delete(queryWrapper);

        return ResultData.success(null);
    }

    @Override
    public List<Activity> listActivityById(String clubId) {
        LambdaQueryWrapper<Activity> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Activity::getClubId, clubId);
        return activityMapper.selectList(wrapper);
    }
}
