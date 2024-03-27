package com.example.backend.controller;

import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.service.ClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class commonUserController {
    @Autowired
    ClubService clubService;
    @PostMapping("/register/")
    public ResultData<Object> clubRegister(@RequestBody @Validated ClubRegisterRequestDTO clubRegisterRequestDTO) {
        return clubService.clubRegister(clubRegisterRequestDTO.getName(), clubRegisterRequestDTO.getPresidentId());
    }
}
