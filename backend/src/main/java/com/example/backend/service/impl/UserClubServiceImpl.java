package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserClubMapper;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.UserClub;
import com.example.backend.service.UserClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class UserClubServiceImpl implements UserClubService {
    @Autowired
    ClubMapper clubMapper;

    @Autowired
    UserClubMapper userClubMapper;

    @Autowired
    UserService userService;

    @Override
    public ResultData<Object> userClubJoin(String userId,String clubId) {

        QueryWrapper<Club> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id",clubId);
        Club club = clubMapper.selectOne(queryWrapper1);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        QueryWrapper<UserClub> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("user_id",userId).eq("club_id",clubId);
        List<UserClub> userClubs = userClubMapper.selectList(queryWrapper2);
        if(!userClubs.isEmpty()){
            throw new BusinessException(ReturnCodes.JOIN_YET,null);
        }

        UserClub userClub = new UserClub();
        userClub.setUserId(parseLong(userId));
        userClub.setClubId(parseLong(clubId));

        userClubMapper.insert(userClub);

        return ResultData.success(null);

    }

    @Override
    public ResultData<Object> userClubPass(String userId,String clubId) {
        QueryWrapper<UserClub> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", userId).eq("club_id",clubId);
        UserClub userClub = userClubMapper.selectOne(queryWrapper1);
        if (userClub == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        userClub.setIsPassed(1);
        userClubMapper.updateById(userClub);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userClubDelete(String userId, String clubId) {
        QueryWrapper<UserClub> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("user_id", userId).eq("club_id",clubId);
        UserClub userClub = userClubMapper.selectOne(queryWrapper1);
        if (userClub == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        QueryWrapper<Club> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("president_id", userId).eq("id",clubId);
        Club club = clubMapper.selectOne(queryWrapper2);
        if(club != null) {
            throw new BusinessException(ReturnCodes.KICK_PRESIDENT,null);
        }

        if(userClub.getIsPassed() != 1){
            throw new BusinessException(ReturnCodes.NOT_PASS_YEET,null);
        }

        userClubMapper.delete(queryWrapper1);

        return ResultData.success(null);
    }

    @Override
    public List<UserClub> getListByUserId(Long userId) {
        LambdaQueryWrapper<UserClub> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserClub::getUserId, userId);
        return userClubMapper.selectList(wrapper);
    }

}
