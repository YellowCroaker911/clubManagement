package com.example.backend.controller;

import com.example.backend.model.dto.club.ClubAdmitRequestDTO;
import com.example.backend.model.dto.club.ClubAlterInfoRequestDTO;
import com.example.backend.model.dto.club.ClubGetInfoRequestDTO;
import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.model.entity.Club;
import com.example.backend.service.ClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/club")
public class ClubController {
    @Autowired
    ClubService clubService;

    @PostMapping("/register/")
    public ResultData<Object> clubRegister(@RequestBody @Validated ClubRegisterRequestDTO clubRegisterRequestDTO) {
        return clubService.clubRegister(clubRegisterRequestDTO.getName(), clubRegisterRequestDTO.getPresidentId());
    }
    @PostMapping("/admit")

    public ResultData<Object> clubAdmit(@RequestBody @Validated ClubAdmitRequestDTO clubAdmitRequestDTO) {
        return clubService.clubAdmit(clubAdmitRequestDTO.getId());
    }

    @PostMapping("/alterInfo")
    public ResultData<Object> clubAlterInfo(@RequestBody @Validated ClubAlterInfoRequestDTO clubAlterInfoRequestDTO){
        return clubService.clubAlterInfo(clubAlterInfoRequestDTO.getId(),clubAlterInfoRequestDTO.getAvatar(),
                clubAlterInfoRequestDTO.getInfo(),clubAlterInfoRequestDTO.getAddress(),clubAlterInfoRequestDTO.getContact());
    }

    @GetMapping("/getInfo")
    public ResultData<Club> clubGetInfo(@RequestBody @Validated ClubGetInfoRequestDTO clubGetInfoRequestDTO){
        return clubService.clubGetInfo(clubGetInfoRequestDTO.getId());
    }

}
