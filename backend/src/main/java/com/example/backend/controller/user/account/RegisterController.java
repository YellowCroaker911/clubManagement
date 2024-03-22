package com.example.backend.controller.user.account;

import com.example.backend.model.dto.user.account.UserRegisterRequestDTO;
import com.example.backend.service.user.account.RegisterService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.Integer.parseInt;

@RestController
public class RegisterController {
    @Autowired
    private RegisterService registerService;

    @PostMapping("/user/account/register/")
    public ResultData register(@RequestBody UserRegisterRequestDTO registerDTO) {
//        if(CommonUtil.checkAnyNullField(registerDTO)){
//            throw new BusinessException(ReturnCodes.NULL_FIELD);
//        }
        String username = registerDTO.getUsername();
        String password = registerDTO.getPassword();
        String confirmedPassword = registerDTO.getConfirmedPassword();
        String role = registerDTO.getRole();
        return registerService.register(username, password, confirmedPassword, role);
    }
}
