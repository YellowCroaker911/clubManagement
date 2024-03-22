package com.example.backend.service.impl.user.account;

import com.example.backend.service.impl.utils.UserDetailsImpl;
import com.example.backend.service.user.account.AdminJudgeService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminJudgeServiceImpl implements AdminJudgeService {


    public boolean judge(){
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();
        ArrayList<SimpleGrantedAuthority> simpleGrantedAuthoritys = (ArrayList<SimpleGrantedAuthority>) loginUser.getAuthorities();
        if (simpleGrantedAuthoritys == null){
            return false;
        }
        for (SimpleGrantedAuthority simpleGrantedAuthority : simpleGrantedAuthoritys){
            if(simpleGrantedAuthority.equals(new SimpleGrantedAuthority("admin"))){
                return true;
            }
        }
        return false;
    }
}
