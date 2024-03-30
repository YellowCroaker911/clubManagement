package com.example.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.backend.exception.BusinessException;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.entity.User;
import com.example.backend.model.vo.UserLoginTokenVO;
import com.example.backend.service.UserService;
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
import static java.lang.Long.parseLong;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserMapper userMapper;
    @Autowired
    ClubMapper clubMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public ResultData<Object> userRegister(String username, String password, String confirmedPassword, String role) {
        if (!password.equals(confirmedPassword)) {
            throw new BusinessException(ReturnCodes.DIFF_PASSWORD, null);
        }

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        List<User> users = userMapper.selectList(queryWrapper);
        if (!users.isEmpty()) {
            throw new BusinessException(ReturnCodes.EXIST_USERNAME, null);
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
    public ResultData<Object> userAlterSelfInfo(String id,String name, String gender, String phone, String email) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        user.setName(name);
        user.setGender(parseInt(gender));
        user.setPhone(phone);
        user.setEmail(email);

        userMapper.updateById(user);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userAlterPassword(String id,String oldPassword, String newPassword, String confirmedPassword) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        if(!passwordEncoder.matches(oldPassword, user.getPassword())){
            throw new BusinessException(ReturnCodes.DIFF_OlD_PASSWORD,null);
        }

        if (!newPassword.equals(confirmedPassword)) {
            throw new BusinessException(ReturnCodes.DIFF_PASSWORD, null);
        }

        String encodedNewPassword = passwordEncoder.encode(newPassword);
        user.setPassword(encodedNewPassword);

        userMapper.updateById(user);

        return ResultData.success(null);
    }

    @Override
    public ResultData<Object> userDelete(String id) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", id);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }
        userMapper.delete(queryWrapper);
        return ResultData.success(null);
    }

    @Override
    public User userGetSelfInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        return userDetails.getUser();
    }

    public ResultData<Object> userUpdateAvatar(String id,String avatarUrl) {

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user = userMapper.selectOne(queryWrapper);
        if (user == null) {
            throw new BusinessException(ReturnCodes.INDEX_NOT_EXIST, null);
        }

        user.setAvatar(avatarUrl);
        userMapper.updateById(user);

        return ResultData.success(null);
    }

}
