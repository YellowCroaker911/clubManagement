package com.example.backend.controller;

import com.example.backend.model.dto.user.UserAlterInfoRequestDTO;
import com.example.backend.model.dto.user.UserAlterPasswordRequestDTO;
import com.example.backend.model.dto.user.UserLoginRequestDTO;
import com.example.backend.model.dto.user.UserRegisterRequestDTO;
import com.example.backend.model.entity.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.service.UserService;
import com.example.backend.utils.CommonConstant;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/account")
public class UserAccountController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResultData<Object> userRegister(@RequestBody @Validated UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.userRegister(userRegisterRequestDTO.getUsername(), userRegisterRequestDTO.getPassword(),
                userRegisterRequestDTO.getConfirmedPassword(), userRegisterRequestDTO.getRole());
    }
    @PostMapping("/getToken")
    public ResultData<UserLoginTokenVO> getToken(@RequestBody @Validated UserLoginRequestDTO userLoginRequestDTO) {
        return userService.userGetToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
    }
    @PostMapping("/alterInfo")
    public ResultData<Object> alterInfo(@RequestBody @Validated UserAlterInfoRequestDTO userAlterInfoRequestDTO) {
        return userService.userAlterInfo(userAlterInfoRequestDTO.getName(),
                userAlterInfoRequestDTO.getGender(),
                userAlterInfoRequestDTO.getPhone(),
                userAlterInfoRequestDTO.getEmail());
    }
    @PostMapping("/alterPassword")
    public ResultData<Object> alterPassword(@RequestBody @Validated UserAlterPasswordRequestDTO userAlterPasswordRequestDTO) {
        return userService.userAlterPassword(userAlterPasswordRequestDTO.getOldPassword(),
                userAlterPasswordRequestDTO.getNewPassword(),userAlterPasswordRequestDTO.getConfirmedPassword());
    }

    @GetMapping("/getSelfInfo")
    public ResultData<User> getSelfInfo() {
        User user = userService.userGetSelfInfo();
        user.setPassword(null);
        return ResultData.success(user);
    }


    /*************用户头像***************/
    @PostMapping("/upload")
    public ResultData<Object> singleFileUpload(@RequestParam("file") MultipartFile file) {
        //@RequestParam("file") MultipartFile file为接收图片参数
        //Long userId,String status 用户Id和状态
        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = CommonUtil.getPhotoName("img",imageFileName);
            Path path = Paths.get(CommonConstant.IMG_FOLDER + fileName);
            //“C:\\框架\\D4\\d4_pc_ui\\src\\assets\\images\\img\\”为本地目录
            Files.write(path, bytes);//写入文件
            String avatar_url=fileName;
            userService.updateAvatar(avatar_url);//dao层方法

//            System.out.println(fileName);
            return ResultData.success(fileName);//返回文件名字
        } catch (IOException e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodes.SYSTEM_ERROR.getCode(), "存图片错误", null);
        }
    }

    @GetMapping("/getAvatar")
    public void getAvatar(HttpServletResponse response) {
        // 获取图片名
        String avatar = userService.userGetSelfInfo().getAvatar();
        // 图片文件
        File file = new File(CommonConstant.IMG_FOLDER + avatar);
        response.setContentType("image/jpg");
        if(!file.exists())
            return;
        CommonUtil.readFileToResponse(response, file);
    }



}
