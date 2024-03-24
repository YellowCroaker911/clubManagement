package com.example.backend.controller.user.account;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.user.account.UserRegisterRequestDTO;
import com.example.backend.service.user.account.RegisterService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Integer.parseInt;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/account/register/")
    public ResultData register(@RequestBody @Validated UserRegisterRequestDTO registerDTO) {

        return registerService.register(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getConfirmedPassword(), registerDTO.getRole());
    }
}
