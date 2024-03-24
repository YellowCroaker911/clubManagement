package com.example.backend.controller.user.account;

import com.example.backend.model.pojo.User;
import com.example.backend.service.user.account.GetInfoService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {
    @Autowired
    private GetInfoService getInfoService;

    @GetMapping("/user/account/info/")
    public ResultData<User> getInfo() {
        return getInfoService.getInfo();
    }
}
