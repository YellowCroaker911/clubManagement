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
import com.example.backend.service.impl.utils.LoginUser;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Long.parseLong;

@Service
public class UserClubServiceImpl implements UserClubService {

    @Autowired
    ClubMapper clubMapper;

    @Autowired
    UserClubMapper userClubMapper;

    @Override
    public ResultData<Object> userClubJoin(String id) {
        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        UserClub userClub = new UserClub();
        userClub.setUserId(loginUser.getId());
        userClub.setClubId(parseLong(id));
        userClub.setIsPassed(0);

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

        QueryWrapper<Club> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id",clubId);
        Club club = clubMapper.selectOne(queryWrapper2);

        return ResultData.success(null);
    }
}
