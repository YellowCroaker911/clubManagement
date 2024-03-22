package com.example.backend.service.impl.club;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.model.pojo.Club;
import com.example.backend.service.club.ClubAdmitService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClubAdmitServiceImpl implements ClubAdmitService {
    @Autowired
    ClubMapper clubMapper;

    @Override
    public ResultData admit(String id) {
        QueryWrapper<Club> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        Club club = clubMapper.selectOne(queryWrapper);
        if (club == null) {
           return ResultData.fail(ReturnCodes.DATABASE_ERROR,"社团索引不存在");
        }
        UpdateWrapper<Club> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("is_admitted", 1);
        clubMapper.update(club, updateWrapper);
        return ResultData.success(null);
    }
}
