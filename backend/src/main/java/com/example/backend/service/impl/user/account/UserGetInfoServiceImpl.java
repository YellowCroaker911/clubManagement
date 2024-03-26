package com.example.backend.service.impl.user.account;

import com.example.backend.model.pojo.User;
import com.example.backend.service.impl.method.LoginUser;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.service.user.account.UserGetInfoService;
import com.example.backend.utils.result.ResultData;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserGetInfoServiceImpl implements UserGetInfoService {

    public ResultData<User> getInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl userDetails = LoginUser.getUserDetails();
        User loginUser = userDetails.getUser();

        loginUser.setPassword(null);
        return ResultData.success(loginUser);
    }
}
