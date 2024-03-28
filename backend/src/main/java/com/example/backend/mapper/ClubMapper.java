package com.example.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.backend.model.entity.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @author oval_m
* @description 针对表【club(社团)】的数据库操作Mapper
* @createDate 2024-03-22 21:30:27
* @Entity generator.domain.Club
*/
@Mapper
public interface ClubMapper extends BaseMapper<Club> {
    List<Club> getClubByUserId(@Param("id") Long id);

    List<Club> getClubByPresidentId(@Param("id") Long id);

}




