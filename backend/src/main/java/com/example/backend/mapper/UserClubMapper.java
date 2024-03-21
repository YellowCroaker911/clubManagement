package com.example.backend.mapper;

import com.example.backend.pojo.UserClub;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【user_club(社员和社团关系表)】的数据库操作Mapper
* @createDate 2024-03-21 16:47:31
* @Entity generator.pojo.UserClub
*/
@Mapper
public interface UserClubMapper extends BaseMapper<UserClub> {

}




