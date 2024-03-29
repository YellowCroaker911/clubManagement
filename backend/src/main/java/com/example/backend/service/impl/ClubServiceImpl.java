package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserClubMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.entity.Club;
import com.example.backend.model.entity.User;
import com.example.backend.model.entity.UserClub;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class ClubServiceImpl implements ClubService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    ClubMapper clubMapper;

    @Autowired
    UserClubMapper userClubMapper;

    @Autowired
    UserService userService;

    @Override
    public ResultData<Object> clubRegister(String userId, String name) {

        QueryWrapper<Club> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("name", name);
        List<Club> clubs = clubMapper.selectList(queryWrapper1);
        if(!clubs.isEmpty()){
            throw new BusinessException(ReturnCodes.EXIST_CLUB_NAME,null);
        }
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", userId);
        List<User> users = userMapper.selectList(queryWrapper2);
        if(users.isEmpty()){
            throw new BusinessException(ReturnCodes.NOT_EXIST_PRESIDENT,null);
        }
        Club club = new Club();
        club.setName(name);
        club.setPresidentId(parseLong(userId));
        clubMapper.insert(club);
        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> clubAdmit(String id) {
        QueryWrapper<Club> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper1);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        club.setIsAdmitted(1);
        clubMapper.updateById(club);

        UserClub userClub = new UserClub();
        userClub.setUserId(club.getPresidentId());
        userClub.setClubId(club.getId());
        userClubMapper.insert(userClub);

        userClub.setIsPassed(1);
        userClubMapper.updateById(userClub);

        return ResultData.success(null);
    }
    @Override
    public ResultData<Object> clubAlterInfo(String id, String info, String address, String contact) {

        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        club.setInfo(info);
        club.setAddress(address);
        club.setContact(contact);
        clubMapper.updateById(club);
        return ResultData.success(null);

    }

    @Override
    public ResultData<Object> clubDelete(String id) {
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }
        clubMapper.delete(queryWrapper);
        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> clubUpdateAvatar(String id, String avatarUrl) {
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        club.setAvatar(avatarUrl);
        clubMapper.updateById(club);

        return ResultData.success(null);
    }

}
