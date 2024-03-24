package com.example.backend.controller.user.account;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.user.account.UserAlterInfoRequestDTO;
import com.example.backend.model.vo.UserLoginTokenVO;
import com.example.backend.service.user.account.AlterInfoService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlterInfoController {
    @Autowired
    private AlterInfoService alterInfoService;

    @PostMapping("/user/account/alter/")
    public ResultData<UserLoginTokenVO> getToken(@RequestBody @Validated UserAlterInfoRequestDTO alterDTO) {
        if(CommonUtil.checkAnyNullField(alterDTO)) {
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        return alterInfoService.alterInfo(alterDTO.getName(),alterDTO.getAvatar(),alterDTO.getGender(),alterDTO.getPhone(),alterDTO.getEmail());
    }
}
