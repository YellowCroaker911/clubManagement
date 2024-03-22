package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.model.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
* @author oval_m
* @description 针对表【user(用户)】的数据库操作Mapper
* @createDate 2024-03-22 21:30:27
* @Entity generator.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




