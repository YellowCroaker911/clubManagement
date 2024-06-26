package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.model.entity.ClubActivity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author oval_m
* @description 针对表【club_activity(社团和活动关系表)】的数据库操作Mapper
* @createDate 2024-03-22 21:30:27
* @Entity generator.domain.ClubActivity
*/
@Mapper
public interface ClubActivityMapper extends BaseMapper<ClubActivity> {

}




