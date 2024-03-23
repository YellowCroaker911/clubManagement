package com.example.backend.controller.club;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.club.ClubAdmitRequestDTO;
import com.example.backend.service.club.ClubAdmitService;
import com.example.backend.service.user.account.AdminJudgeService;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ClubAdmitController {
    @Autowired
    private ClubAdmitService clubAdmitService;

    @Autowired
    private AdminJudgeService adminJudgeService;

    @PostMapping("/club/admit/")
    public ResultData admit(@RequestBody ClubAdmitRequestDTO clubAdmitRequestDTO) {
        if(CommonUtil.checkAnyNullField(clubAdmitRequestDTO)){
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        if(!adminJudgeService.judge()){
            return ResultData.fail(ReturnCodes.NOT_ADMIN,null);
        }
        String id = clubAdmitRequestDTO.getId();
        return clubAdmitService.admit(id);
    }
}
