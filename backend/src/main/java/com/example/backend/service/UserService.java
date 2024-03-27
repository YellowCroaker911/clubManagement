package com.example.backend.service;

import com.example.backend.model.entity.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.utils.result.ResultData;

import java.util.List;

public interface UserService {
    ResultData<Object> userRegister(String username, String password, String confirmedPassword, String role);
    ResultData<UserLoginTokenVO> userGetToken(String username, String password);
    ResultData<Object> userAlterInfo(String name, String avatar, String gender, String phone, String email);
    ResultData<Object> userAlterPassword(String oldPassword,String newPassword,String confirmedPassword);
    ResultData<User> userGetSelfInfo();
    ResultData<List<User>> userCheckMember(String id);
}
