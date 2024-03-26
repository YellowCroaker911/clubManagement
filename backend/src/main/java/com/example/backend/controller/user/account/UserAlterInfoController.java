package com.example.backend.controller.user.account;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.user.account.UserAlterInfoRequestDTO;
import com.example.backend.service.user.account.UserAlterInfoService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserAlterInfoController {
    @Autowired
    private UserAlterInfoService userAlterInfoService;

    @PostMapping("/user/account/alterInfo/")
    public ResultData<Object> alterInfo(@RequestBody @Validated UserAlterInfoRequestDTO alterDTO) {
        if (CommonUtil.checkAnyNullField(alterDTO)) {
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        return userAlterInfoService.alterInfo(alterDTO.getName(), alterDTO.getAvatar(), alterDTO.getGender(), alterDTO.getPhone(), alterDTO.getEmail());
    }
}
