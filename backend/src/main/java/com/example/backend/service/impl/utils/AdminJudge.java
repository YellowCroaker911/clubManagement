package com.example.backend.service.impl.utils;

import com.example.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;

public class AdminJudge {
    public static boolean judge(UserDetailsImpl loginUser){
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
