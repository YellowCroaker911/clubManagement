package com.example.backend.controller;

import com.example.backend.model.dto.user.UserAlterInfoRequestDTO;
import com.example.backend.model.dto.user.UserAlterPasswordRequestDTO;
import com.example.backend.model.dto.user.UserLoginRequestDTO;
import com.example.backend.model.dto.user.UserRegisterRequestDTO;
import com.example.backend.model.entity.User;
import com.example.backend.model.vo.user.UserLoginTokenVO;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.CommonConstant;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/account")
public class UserAccountController {
    @Autowired
    UserService userService;

    @Autowired
    ClubService clubService;

    //用户注册
    @PostMapping("/register")
    public ResultData<Object> userRegister(@RequestBody @Validated UserRegisterRequestDTO userRegisterRequestDTO) {
        return userService.userRegister(userRegisterRequestDTO.getUsername(), userRegisterRequestDTO.getPassword(),
                userRegisterRequestDTO.getConfirmedPassword(), userRegisterRequestDTO.getRole());
    }

    // 用户登录，返回jwt-token
    @PostMapping("/getToken")
    public ResultData<UserLoginTokenVO> getToken(@RequestBody @Validated UserLoginRequestDTO userLoginRequestDTO) {
        return userService.userGetToken(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
    }

    // 用户修改自身信息
    @PostMapping("/alterSelfInfo")
    public ResultData<Object> alterSelfInfo(@RequestBody @Validated UserAlterInfoRequestDTO userAlterInfoRequestDTO) {
        User loginUser = userService.userGetSelfInfo();
        return userService.userAlterSelfInfo(loginUser.getId().toString(),userAlterInfoRequestDTO.getName(),
                userAlterInfoRequestDTO.getGender(),
                userAlterInfoRequestDTO.getPhone(),
                userAlterInfoRequestDTO.getEmail());
    }

    // 用户修改自身密码
    @PostMapping("/alterPassword")
    public ResultData<Object> alterPassword(@RequestBody @Validated UserAlterPasswordRequestDTO userAlterPasswordRequestDTO) {
        User loginUser = userService.userGetSelfInfo();
        return userService.userAlterPassword(loginUser.getId().toString(),userAlterPasswordRequestDTO.getOldPassword(),
                userAlterPasswordRequestDTO.getNewPassword(),userAlterPasswordRequestDTO.getConfirmedPassword());
    }

    // 用户获取自身信息
    @GetMapping("/getSelfInfo")
    public ResultData<User> getSelfInfo() {
        User user = userService.userGetSelfInfo();
        user.setPassword(null);
        return ResultData.success(user);
    }

    /*************用户头像***************/
    /**
     * 上传修改用户头像
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResultData<Object> singleFileUpload(@RequestParam("file") MultipartFile file) {
        //@RequestParam("file") MultipartFile file为接收图片参数
        //Long userId,String status 用户Id和状态
        User loginUser = userService.userGetSelfInfo();

        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = CommonUtil.getPhotoName("img",imageFileName);
            Path path = Paths.get(CommonConstant.IMG_FOLDER + fileName);
            //“C:\\框架\\D4\\d4_pc_ui\\src\\assets\\images\\img\\”为本地目录
            Files.write(path, bytes);//写入文件
            String avatar_url =fileName;
            userService.userUpdateAvatar(loginUser.getId().toString(),avatar_url);//dao层方法
            return ResultData.success(fileName);//返回文件名
        } catch (IOException e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodes.SYSTEM_ERROR.getCode(), "上传图片错误", null);
        }
    }

    /**
     * 获取登录用户头像，返回base64
     * @return
     */
    @GetMapping("/getAvatar")
    public ResultData<String> getAvatar() {
        User loginUser = userService.userGetSelfInfo();
        // 获取图片名称
        String avatar = loginUser.getAvatar();
        // 获取图片并转成Base64
        return ResultData.success(CommonUtil.convertImageToBase64Str(CommonConstant.IMG_FOLDER + avatar));
    }

}
