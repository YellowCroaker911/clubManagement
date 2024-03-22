package com.example.backend.controller.club;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.service.club.ClubRegisterService;
import com.example.backend.service.user.account.AdminJudgeService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClubRegisterController {
    @Autowired
    private ClubRegisterService clubRegisterService;

    @Autowired
    private AdminJudgeService adminJudgeService;

    @PostMapping("/club/register/")
    public ResultData register(@RequestParam ClubRegisterRequestDTO clubRegisterRequestDTO) {
        if(CommonUtil.checkAnyNullField(clubRegisterRequestDTO)){
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }

        System.out.println(clubRegisterRequestDTO);
        if(!adminJudgeService.judge()){
            return ResultData.fail(ReturnCodes.NOT_ADMIN,null);
        }
        String name = clubRegisterRequestDTO.getName();
        String president_id = clubRegisterRequestDTO.getPresident_id();
        return clubRegisterService.register(name, president_id);
    }
}
