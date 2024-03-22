package com.example.backend.service.user.account;

import com.example.backend.model.vo.UserLoginTokenVO;
import com.example.backend.utils.result.ResultData;

public interface LoginService {
    ResultData<UserLoginTokenVO> getToken(String username, String password);
}
