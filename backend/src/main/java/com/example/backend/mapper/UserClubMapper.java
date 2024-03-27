package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.model.entity.UserClub;
import org.apache.ibatis.annotations.Mapper;

/**
* @author Pfeistorch
* @description 针对表【user_club(社员和社团关系表)】的数据库操作Mapper
* @createDate 2024-03-27 15:32:28
* @Entity generator.pojo.UserClub
*/
@Mapper
public interface UserClubMapper extends BaseMapper<UserClub> {

}




