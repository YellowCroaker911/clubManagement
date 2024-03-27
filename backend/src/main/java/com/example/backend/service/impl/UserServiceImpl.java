package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.entity.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.service.UserService;
import com.example.backend.service.impl.utils.LoginUser;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.utils.JwtUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.lang.Integer.parseInt;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultData<Object> userRegister(String username, String password, String confirmedPassword, String role) {
        if (!password.equals(confirmedPassword)) {
            throw new BusinessException(ReturnCodes.DIFF_PASSWORD,null);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if(!users.isEmpty()){
            throw new BusinessException(ReturnCodes.EXIST_USERNAME,null);
        }

        String encodedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setUsername(username);
        user.setPassword(encodedPassword);
        user.setRole(parseInt(role));
        userMapper.insert(user);

        return ResultData.success(null);
    }

    @Override
    public ResultData<UserLoginTokenVO> userGetToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);  // 登录失败，会自动处理
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User user = loginUser.getUser();
        String jwt = JwtUtil.createJWT(user.getId().toString());
        UserLoginTokenVO tokenVO = new UserLoginTokenVO();
        tokenVO.setToken(jwt);
        return ResultData.success(tokenVO);
    }

    @Override
    public ResultData<Object> userAlterInfo(String name, String avatar, String gender, String phone, String email) {
        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginUser.getId());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
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

    @Override
    public ResultData<Object> userAlterPassword(String oldPassword,String newPassword, String confirmedPassword) {
        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        String encodedOldPassword = passwordEncoder.encode(newPassword);

        if(!loginUser.getPassword().equals(encodedOldPassword)){
            throw new BusinessException(ReturnCodes.DIFF_OlD_PASSWORD,null);
        }

        if (!newPassword.equals(confirmedPassword)) {
            throw new BusinessException(ReturnCodes.DIFF_PASSWORD,null);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", loginUser.getId());
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST,null);
        }

        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        String encodedNewPassword = passwordEncoder.encode(newPassword);
        updateWrapper.set("password",encodedNewPassword);

        userMapper.update(user, updateWrapper);

        return ResultData.success(null);
    }

    @Override
    public ResultData<User> userGetSelfInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        loginUser.setPassword(null);
        return ResultData.success(loginUser);
    }


}
