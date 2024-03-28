package com.example.backend.service;

import com.example.backend.model.entity.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.utils.result.ResultData;

import java.util.List;

public interface UserService {

    ResultData<Object> userRegister(String username, String password, String confirmedPassword, String role);
    ResultData<Object> userAlterSelfInfo(String id,String name, String gender, String phone, String email);
    ResultData<Object> userAlterPassword(String id,String oldPassword,String newPassword,String confirmedPassword);
    ResultData<Object> userDelete(String id);
    ResultData<UserLoginTokenVO> userGetToken(String username, String password);
    User userGetSelfInfo();
    ResultData<Object> userUpdateAvatar(String id,String avatarUrl);
}
