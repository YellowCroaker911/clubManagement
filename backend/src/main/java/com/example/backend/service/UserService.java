package com.example.backend.service;

import com.example.backend.model.entity.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.utils.result.ResultData;

public interface UserService {
    public ResultData<Object> userRegister(String username, String password, String confirmedPassword, String role);
    public ResultData<UserLoginTokenVO> userGetToken(String uusername, String password);
    public ResultData<Object> userAlterInfo(String name, String avatar, String gender, String phone, String email);
    public ResultData<Object> userAlterPassword(String oldPassword,String newPassword,String confirmedPassword);
    public ResultData<User> userGetSelfInfo();
}
