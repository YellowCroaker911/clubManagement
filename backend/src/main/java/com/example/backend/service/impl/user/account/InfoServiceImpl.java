package com.example.backend.service.impl.user.account;

import com.example.backend.model.pojo.User;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.service.user.account.InfoService;
import com.example.backend.utils.result.ResultData;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService{

    public ResultData<User> getInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        User user = loginUser.getUser();
//        Map<String, String> map = new HashMap<>();
//        map.put("id", user.getId().toString());
//        map.put("username", user.getUsername());
        user.setPassword(null);
        return ResultData.success(user);
    }
}
