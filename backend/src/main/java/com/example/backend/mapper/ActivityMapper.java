package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.model.entity.Activity;
import org.apache.ibatis.annotations.Mapper;

/**
* @author oval_m
* @description 针对表【activity(活动)】的数据库操作Mapper
* @createDate 2024-03-22 21:30:27
* @Entity generator.domain.Activity
*/
@Mapper
public interface ActivityMapper extends BaseMapper<Activity> {

}




