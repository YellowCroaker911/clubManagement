package com.example.backend.controller.user.account;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.user.account.UserRegisterRequestDTO;
import com.example.backend.service.user.account.UserRegisterService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserRegisterController {
    @Autowired
    private UserRegisterService userRegisterService;

    @PostMapping("/user/account/register/")
    public ResultData<Object> userRegister(@RequestBody @Validated UserRegisterRequestDTO registerDTO) {
        if (CommonUtil.checkAnyNullField(registerDTO)) {
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        return userRegisterService.userRegister(registerDTO.getUsername(), registerDTO.getPassword(), registerDTO.getConfirmedPassword(), registerDTO.getRole());
    }
}
