package com.example.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.pojo.User;
import com.example.backend.service.impl.method.LoginUser;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.service.user.account.UserAlterInfoService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static java.lang.Integer.parseInt;

@Service
public class UserAlterInfoServiceImpl implements UserAlterInfoService {
    @Autowired
    UserMapper userMapper;
    @Override
    public ResultData<Object> alterInfo(String name, String avatar, String gender, String phone, String email) {
        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginUser.getId());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(ReturnCodes.DATABASE_ERROR);
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("name", name);
        updateWrapper.set("avatar", avatar);
        updateWrapper.set("gender", parseInt(gender));
        updateWrapper.set("phone", phone);
        updateWrapper.set("email", email);

        userMapper.update(user, updateWrapper);

        return ResultData.success(null);
    }
}
