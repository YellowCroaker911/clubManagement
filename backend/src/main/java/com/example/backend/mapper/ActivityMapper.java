package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.model.entity.Activity;
import com.example.backend.model.entity.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author oval_m
* @description 针对表【activity(活动)】的数据库操作Mapper
* @createDate 2024-03-22 21:30:27
* @Entity generator.domain.Activity
*/
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {
    List<Activity> getActivitiesByClubId(@Param("id") Long id);

    List<Activity> getActivitiesByUserId(@Param("id") Long id);
}




