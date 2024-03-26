package com.example.backend.controller.user.account;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.user.account.UserLoginRequestDTO;
import com.example.backend.model.vo.UserLoginTokenVO;
import com.example.backend.service.user.account.UserLoginService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/user/account/token/")
    public ResultData<UserLoginTokenVO> getToken(@RequestBody @Validated UserLoginRequestDTO loginDTO) {
        if(CommonUtil.checkAnyNullField(loginDTO)) {
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        return userLoginService.getToken(loginDTO.getUsername(), loginDTO.getPassword());
    }
}
