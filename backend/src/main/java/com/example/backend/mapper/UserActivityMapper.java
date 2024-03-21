package com.example.backend.mapper;

import com.example.backend.pojo.UserActivity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author Pfeistorch
* @description 针对表【user_activity(社员和活动关系表)】的数据库操作Mapper
* @createDate 2024-03-21 16:47:28
* @Entity generator.pojo.UserActivity
*/
@Mapper
public interface UserActivityMapper extends BaseMapper<UserActivity> {

}




