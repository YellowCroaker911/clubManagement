package com.example.backend.service.impl.utils;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.entity.User;
import com.example.backend.utils.result.ReturnCodes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements org.springframework.security.core.userdetails.UserDetails {

    private User user;

    //根据账号身份返回授权
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(user.getRole().equals(0)){
            return null;
        }
        List<SimpleGrantedAuthority> authoritys = new ArrayList<>();
        authoritys.add(new SimpleGrantedAuthority("admin"));
        return authoritys;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
