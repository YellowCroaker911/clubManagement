package com.example.backend.controller;

import com.example.backend.service.ClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    ClubService clubService;
    // 社团注册通过
    @GetMapping("/admit")
    public ResultData<Object> clubAdmit(@RequestParam String id) {
        return clubService.clubAdmit(id);
    }

    // 注销社团
    @GetMapping("delete")
    public ResultData<Object> clubDelete(String id){
        return clubService.clubDelete(id);
    }

}
