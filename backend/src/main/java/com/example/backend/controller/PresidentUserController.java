package com.example.backend.controller;

import com.example.backend.model.dto.club.ClubAlterInfoRequestDTO;
import com.example.backend.service.ClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/president")
public class PresidentUserController {
    @Autowired
    ClubService clubService;
    @PostMapping("/alterInfo")
    public ResultData<Object> clubAlterInfo(@RequestBody @Validated ClubAlterInfoRequestDTO clubAlterInfoRequestDTO){
        return clubService.clubAlterInfo(clubAlterInfoRequestDTO.getId(),clubAlterInfoRequestDTO.getAvatar(),
                clubAlterInfoRequestDTO.getInfo(),clubAlterInfoRequestDTO.getAddress(),clubAlterInfoRequestDTO.getContact());
    }
}
