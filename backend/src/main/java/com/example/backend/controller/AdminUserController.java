package com.example.backend.controller;

import com.example.backend.model.dto.club.ClubRegisterRequestDTO;
import com.example.backend.model.dto.id.Id1DTO;
import com.example.backend.service.ClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    ClubService clubService;

    @PostMapping("/admit")
    public ResultData<Object> clubAdmit(@RequestBody @Validated Id1DTO id1DTO) {
        return clubService.clubAdmit(id1DTO.getId());
    }

}
