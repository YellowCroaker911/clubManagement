package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.pojo.Club;
import com.example.backend.model.pojo.User;
import com.example.backend.service.ClubService;
import com.example.backend.service.impl.method.AdminJudge;
import com.example.backend.service.impl.method.LoginUser;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Long.parseLong;

@Service
public class ClubServiceImpl implements ClubService {
    @Autowired
    ClubMapper clubMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public ResultData<Object> clubAdmit(String id) {
        if(!AdminJudge.judge(LoginUser.getUserDetails())){
            throw new BusinessException(ReturnCodes.NOT_ADMIN,null);
        }
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.DATABASE_ERROR,"索引不存在");
        }
        UpdateWrapper<Club> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_admitted", 1);
        clubMapper.update(club, updateWrapper);
        return ResultData.success(null);
    }
    @Override
    public ResultData<Object> clubAlterInfo(String id,String avatar, String info, String address, String contact) {
        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        if(!loginUser.getId().toString().equals(id)){
            throw new BusinessException(ReturnCodes.NOT_PRESIDENT,null);
        }

        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.DATABASE_ERROR,"索引不存在");
        }

        UpdateWrapper<Club> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("avatar", avatar);
        updateWrapper.set("info", info);
        updateWrapper.set("address", address);
        updateWrapper.set("contact", contact);
        clubMapper.update(club, updateWrapper);
        return ResultData.success(null);

    }

    @Override
    public ResultData<Club> clubGetInfo(String id) {
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
            throw new BusinessException(ReturnCodes.DATABASE_ERROR,"索引不存在");
        }

        return ResultData.success(club);
    }

    @Override
    public ResultData<Object> clubRegister(String name, String president_id) {
        QueryWrapper<Club> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("name", name);
        List<Club> clubs = clubMapper.selectList(queryWrapper1);
        if(!clubs.isEmpty()){
            throw new BusinessException(ReturnCodes.EXIST_CLUB_NAME,null);
        }
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", president_id);
        List<User> users = userMapper.selectList(queryWrapper2);
        if(users.isEmpty()){
            throw new BusinessException(ReturnCodes.NOT_EXIST_PRESIDENT,null);
        }
        Club club = new Club();
        club.setName(name);
        club.setPresidentId(parseLong(president_id));
        clubMapper.insert(club);
        return ResultData.success(null);
    }

}
