package com.example.backend.controller.club;

import com.example.backend.service.club.ClubRegisterService;
import com.example.backend.service.user.account.AdminJudgeService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class ClubRegisterController {
    @Autowired
    private ClubRegisterService clubRegisterService;

    @Autowired
    private AdminJudgeService adminJudgeService;

    @PostMapping("/club/register/")
    public ResultData register(@RequestParam Map<String, String> map) {
        System.out.println(map);
        if(!adminJudgeService.judge()){
            return ResultData.fail(101,null);
        }
        String name = map.get("name");
        String president_id = map.get("president_id");
        return clubRegisterService.register(name,president_id);
    }
}
