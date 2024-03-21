package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.pojo.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Pfeistorch
 * @description 针对表【user(用户)】的数据库操作Mapper
 * @createDate 2024-03-20 23:16:08
 * @Entity generator.pojo.User
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
