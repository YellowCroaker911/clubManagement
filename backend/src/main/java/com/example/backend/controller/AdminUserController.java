package com.example.backend.controller;

import com.example.backend.service.ClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    ClubService clubService;
    // 社团注册通过
    @PostMapping("/admit")
    public ResultData<Object> clubAdmit(@RequestParam @NotNull String id) {
        return clubService.clubAdmit(id);
    }

    // 注销社团
    @PostMapping("delete")
    public ResultData<Object> clubDelete(@RequestParam @NotNull  String id){
        return clubService.clubDelete(id);
    }

}
