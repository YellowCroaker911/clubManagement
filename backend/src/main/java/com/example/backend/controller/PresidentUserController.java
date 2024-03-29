package com.example.backend.controller;

import com.example.backend.mapper.ActivityMapper;
import com.example.backend.mapper.ClubMapper;
import com.example.backend.mapper.UserMapper;
import com.example.backend.model.dto.activity.ActivityAlterInfoRequestDTO;
import com.example.backend.model.dto.activity.ActivityReleaseRequestDTO;
import com.example.backend.model.dto.club.ClubAlterInfoRequestDTO;
import com.example.backend.service.ActivityService;
import com.example.backend.service.ClubService;
import com.example.backend.service.UserClubService;
import com.example.backend.service.UserService;
import com.example.backend.utils.CommonConstant;
import com.example.backend.utils.CommonUtil;
import com.example.backend.utils.result.ResultData;
import com.example.backend.utils.result.ReturnCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;

@RestController
@RequestMapping("/president")
public class PresidentUserController {
    @Autowired
    UserService userService;
    @Autowired
    ClubService clubService;
    @Autowired
    UserClubService userClubService;
    @Autowired
    ActivityService activityService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    ClubMapper clubMapper;
    @Autowired
    ActivityMapper activityMapper;
    // 修改社团信息
    @PostMapping("/alterInfo")
    public ResultData<Object> clubAlterInfo(@RequestBody @Validated ClubAlterInfoRequestDTO clubAlterInfoRequestDTO){
        return clubService.clubAlterInfo(clubAlterInfoRequestDTO.getId(),
                clubAlterInfoRequestDTO.getInfo(),clubAlterInfoRequestDTO.getAddress(),clubAlterInfoRequestDTO.getContact());
    }

    // 社员加入通过
    @PostMapping("/pass")
    public ResultData<Object> clubPass(@RequestParam @NotNull  String userId, @RequestParam @NotNull  String clubId){
        return userClubService.userClubPass(userId,clubId);
    }

    // 社长踢人
    @PostMapping("kick")
    public ResultData<Object> userKick(@RequestParam @NotNull   String userId, @RequestParam @NotNull  String clubId){
        return userClubService.userClubDelete(userId,clubId);
    }

    // 活动发布
    @PostMapping("/activityRelease")
    public ResultData<Object> activityRelease(@RequestBody @Validated ActivityReleaseRequestDTO activityReleaseRequestDTO){
        System.out.println(activityReleaseRequestDTO.getBeginTime());
        System.out.println(new Date());
        return activityService.activityRelease(activityReleaseRequestDTO.getClubId(),activityReleaseRequestDTO.getName(),
                activityReleaseRequestDTO.getInfo(), activityReleaseRequestDTO.getTitle(),
                activityReleaseRequestDTO.getBeginTime(),activityReleaseRequestDTO.getEndTime(),
                activityReleaseRequestDTO.getSignBeginTime(),activityReleaseRequestDTO.getSignEndTime(),
                activityReleaseRequestDTO.getAddress(),activityReleaseRequestDTO.getSign(),activityReleaseRequestDTO.getMoney());
    }

    // 活动修改和活动总结发布以及管理活动签到
    @PostMapping("/activityAlterInfo")
    public ResultData<Object> activityAlterInfo(@RequestBody @Validated ActivityAlterInfoRequestDTO activityAlterInfoRequestDTO) {
        return activityService.activityAlterInfo(activityAlterInfoRequestDTO.getId(),activityAlterInfoRequestDTO.getName(),
                activityAlterInfoRequestDTO.getInfo(), activityAlterInfoRequestDTO.getTitle(),
                activityAlterInfoRequestDTO.getBeginTime(),activityAlterInfoRequestDTO.getEndTime(),
                activityAlterInfoRequestDTO.getSignBeginTime(),activityAlterInfoRequestDTO.getSignEndTime(),
                activityAlterInfoRequestDTO.getAddress(),activityAlterInfoRequestDTO.getSign(),activityAlterInfoRequestDTO.getMoney(),
                activityAlterInfoRequestDTO.getSummary());
    }

    // 活动删除
    @PostMapping("/activityDelete")
    public ResultData<Object> activityDelete(@RequestParam @NotNull  String id){
        return activityService.activityDelete(id);
    }


    /*************社团头像***************/
    /**
     * 上传修改社团头像
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResultData<Object> clubFileUpload(@RequestParam("file") MultipartFile file,@RequestParam @NotNull String id)  {
        //@RequestParam("file") MultipartFile file为接收图片参数

        try {
            byte[] bytes = file.getBytes();
            String imageFileName = file.getOriginalFilename();
            String fileName = CommonUtil.getPhotoName("img",imageFileName);
            Path path = Paths.get(CommonConstant.IMG_FOLDER + fileName);
            //“C:\\框架\\D4\\d4_pc_ui\\src\\assets\\images\\img\\”为本地目录
            Files.write(path, bytes);//写入文件
            String avatar_url = fileName;
            clubService.clubUpdateAvatar(id,avatar_url);//dao层方法
            return ResultData.success(fileName);//返回文件名
        } catch (IOException e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodes.SYSTEM_ERROR.getCode(), "上传图片错误", null);
        }
    }
}
