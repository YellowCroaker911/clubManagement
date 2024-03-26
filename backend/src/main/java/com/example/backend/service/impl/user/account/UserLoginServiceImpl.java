package com.example.backend.service.impl.user.account;

import com.example.backend.model.pojo.User;
import com.example.backend.model.vo.UserLoginTokenVO;
import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.service.user.account.UserLoginService;
import com.example.backend.utils.JwtUtil;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public ResultData<UserLoginTokenVO> getToken(String username, String password) {
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
}
