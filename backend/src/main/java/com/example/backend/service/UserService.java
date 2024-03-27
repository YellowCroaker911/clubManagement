package com.example.backend.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.backend.model.pojo.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.utils.result.ResultData;

public interface UserService extends IService<User> {
    public ResultData<Object> userAlterInfo(String name, String gender, String phone, String email);
    public ResultData<Object> userAlterPassword(String oldPassword,String newPassword,String confirmedPassword);
    public User userGetInfo();
    public ResultData<UserLoginTokenVO> userGetToken(String uusername, String password);
    public ResultData<Object> userRegister(String username, String password, String confirmedPassword, String role);
    void updateAvatar(String avatarUrl);
}
