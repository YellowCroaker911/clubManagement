package com.example.backend.controller.club;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.service.club.ClubRegisterService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubRegisterController {
    @Autowired
    private ClubRegisterService clubRegisterService;

    @PostMapping("/club/register/")
    public ResultData<Object> clubRegister(@RequestBody ClubRegisterRequestDTO clubRegisterRequestDTO) {
        if(CommonUtil.checkAnyNullField(clubRegisterRequestDTO)){
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        String name = clubRegisterRequestDTO.getName();
        String president_id = clubRegisterRequestDTO.getPresident_id();
        return clubRegisterService.clubRegister(name, president_id);
    }
}
