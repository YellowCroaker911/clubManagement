package com.example.backend.service.impl.user.account;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.pojo.User;
import com.example.backend.service.user.account.RegisterService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

@Service
public class RegisterServiceImpl implements RegisterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultData register(String username, String password, String confirmedPassword, String role) {
        Map<String, String> map = new HashMap<>();
        if (username == null) {
            return ResultData.fail(201,null);
        }
        if (password == null || confirmedPassword == null) {
            return ResultData.fail(202,null);
        }

        username = username.trim();
        if (username.length() == 0) {
            return ResultData.fail(201,null);
        }

        if (password.length() == 0 || confirmedPassword.length() == 0) {
            return ResultData.fail(202,null);
        }

        if (username.length() > 20) {
            return ResultData.fail(203,null);
        }

        if (password.length() > 20 || confirmedPassword.length() > 20) {
            return ResultData.fail(204,null);
        }

        if (!password.equals(confirmedPassword)) {
            return ResultData.fail(205,null);
        }
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            return ResultData.fail(206,null);
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(parseInt(role));
        userMapper.insert(user);

        return ResultData.success(map);
    }
}
