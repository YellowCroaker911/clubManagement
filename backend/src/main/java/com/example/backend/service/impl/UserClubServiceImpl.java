package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserClubMapper;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserClub;
import com.example.backend.service.UserClubService;
import com.example.backend.service.UserService;
import com.example.backend.service.impl.utils.UserDetailsImpl;
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

        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",clubId);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
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

        UpdateWrapper<UserClub> updateWrapper1 = new UpdateWrapper<>();
        updateWrapper1.set("is_passed", 1);
        userClubMapper.update(userClub,updateWrapper1);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userClubDelete(String userId, String clubId) {
        QueryWrapper<UserClub> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("club_id",clubId);
        UserClub userClub = userClubMapper.selectOne(queryWrapper);
        if (userClub == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        userClubMapper.delete(queryWrapper);

        return ResultData.success(null);
    }

}
