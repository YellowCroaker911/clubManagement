package com.example.backend.controller.user.account;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.user.account.UserAlterPasswordDTO;
import com.example.backend.service.user.account.UserAlterPasswordService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAlterPasswordController {

    @Autowired
    private UserAlterPasswordService userAlterPasswordService;

    @PostMapping("/user/account/alterPassword/")
    public ResultData<Object> alterPassword(@RequestBody @Validated UserAlterPasswordDTO alterPasswordDTO) {
        if(CommonUtil.checkAnyNullField(alterPasswordDTO)) {
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        return userAlterPasswordService.alterPassword(alterPasswordDTO.getPassword(),alterPasswordDTO.getConfirmedPassword());
    }
}
