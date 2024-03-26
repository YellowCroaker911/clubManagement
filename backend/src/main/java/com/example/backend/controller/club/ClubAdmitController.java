package com.example.backend.controller.club;

import com.example.backend.exception.BusinessException;
import com.example.backend.model.dto.club.ClubAdmitRequestDTO;
import com.example.backend.service.club.ClubAdmitService;
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

    @PostMapping("/club/admit/")

    public ResultData<Object> clubAdmit(@RequestBody ClubAdmitRequestDTO clubAdmitRequestDTO) {
        if(CommonUtil.checkAnyNullField(clubAdmitRequestDTO)){
            throw new BusinessException(ReturnCodes.NULL_FIELD);
        }
        String id = clubAdmitRequestDTO.getId();
        return clubAdmitService.clubAdmit(id);
    }
}
