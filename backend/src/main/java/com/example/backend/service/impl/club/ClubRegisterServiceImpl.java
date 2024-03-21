package com.example.backend.service.impl.club;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.Club;
import com.example.backend.pojo.User;
import com.example.backend.service.club.ClubRegisterService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Long.parseLong;

@Service
public class ClubRegisterServiceImpl implements ClubRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ClubMapper clubMapper;

    public ResultData register(String name, String president_id) {
        Map<String, String> map = new HashMap<>();
        if (name == null) {
            return ResultData.fail(ReturnCodes.EMPTY_CLUB_NAME,null);
        }
        if(name.length() > 20){
            return ResultData.fail(ReturnCodes.TOO_LONG_CLUB_NAME,null);
        }
        QueryWrapper<Club> queryWrapper1 = new QueryWrapper<>();
        queryWrapper1.eq("name", name);
        List<Club> clubs = clubMapper.selectList(queryWrapper1);
        if(!clubs.isEmpty()){
            return ResultData.fail(ReturnCodes.EXIST_CLUB_NAME,null);
        }
        QueryWrapper<User> queryWrapper2 = new QueryWrapper<>();
        queryWrapper2.eq("id", president_id);
        List<User> users = userMapper.selectList(queryWrapper2);
        if(users.isEmpty()){
            return ResultData.fail(ReturnCodes.NOT_EXIST_PRESIDENT,null);
        }
        Club club = new Club();
        club.setName(name);
        club.setPresidentId(parseLong(president_id));
        clubMapper.insert(club);
        return ResultData.success(map);
    }
}
