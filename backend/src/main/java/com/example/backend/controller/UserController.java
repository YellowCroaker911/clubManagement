package com.example.backend.controller;

import com.example.backend.model.dto.user.UserAlterInfoRequestDTO;
import com.example.backend.model.dto.user.UserAlterPasswordRequestDTO;
import com.example.backend.model.dto.user.UserLoginRequestDTO;
import com.example.backend.model.dto.user.UserRegisterRequestDTO;
import com.example.backend.model.pojo.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.service.UserService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/alterInfo")
    public ResultData<Object> alterInfo(@RequestBody @Validated UserAlterInfoRequestDTO userAlterInfoRequestDTO) {
        return userService.userAlterInfo(userAlterInfoRequestDTO.getName(), userAlterInfoRequestDTO.getAvatar(),
                userAlterInfoRequestDTO.getGender(), userAlterInfoRequestDTO.getPhone(), userAlterInfoRequestDTO.getEmail());
    }

    @PostMapping("/alterPassword")
    public ResultData<Object> alterPassword(@RequestBody @Validated UserAlterPasswordRequestDTO userAlterPasswordRequestDTO) {
        return userService.userAlterPassword(userAlterPasswordRequestDTO.getOldPassword(),
                userAlterPasswordRequestDTO.getNewPassword(),userAlterPasswordRequestDTO.getConfirmedPassword());
    }

    @PostMapping("/getInfo")
    public ResultData<User> getInfo() {
        return userService.userGetInfo();
    }

    @PostMapping("/getToken")
    public ResultData<UserLoginTokenVO> getToken(@RequestBody @Validated UserLoginRequestDTO userLoginRequestDTO) {
        return userService.userGetToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
    }

    @PostMapping("/register")
    public ResultData<Object> userRegister(@RequestBody @Validated UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.userRegister(userRegisterRequestDTO.getUsername(), userRegisterRequestDTO.getPassword(),
                userRegisterRequestDTO.getConfirmedPassword(), userRegisterRequestDTO.getRole());
    }
}
