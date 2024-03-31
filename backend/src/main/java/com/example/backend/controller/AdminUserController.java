package com.example.backend.controller;

import com.example.backend.service.AdminService;
import com.example.backend.service.ClubService;
import com.example.backend.utils.result.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/admin")
public class AdminUserController {
    @Autowired
    ClubService clubService;

    @Autowired
    AdminService adminService;
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

    // 数据备份
    @GetMapping("/backup")
    public ResultData<Object> backup() {
        return adminService.backup();
    }

    // 获取备份文件夹信息
    @GetMapping("getAllBackUpFiles")
    public ResultData<Object> getAllBackUpFiles(){
        return ResultData.success(adminService.getAllBackUpFiles());
    }

    // 数据备份文件下载
//    @GetMapping("/download")
//    public ResultData<ResponseEntity<Resource>> download() {
//        return adminService.download();
//    }
}
