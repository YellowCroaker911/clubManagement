package com.example.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.pojo.User;
import com.example.backend.service.impl.method.LoginUser;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.service.user.account.UserAlterPasswordService;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserAlterPasswordServiceImpl implements UserAlterPasswordService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultData<Object> alterPassword(String password, String confirmedPassword) {
        if (!password.equals(confirmedPassword)) {
            throw new BusinessException(ReturnCodes.DIFF_PASSWORD,null);
        }

        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginUser.getId());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(ReturnCodes.DATABASE_ERROR);
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        String encodedPassword = passwordEncoder.encode(password);
        updateWrapper.set("password",encodedPassword);

        userMapper.update(user, updateWrapper);

        return ResultData.success(null);
    }
}
