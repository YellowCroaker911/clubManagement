package com.example.backend.model.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社员和社团关系表
 * @TableName user_club
 */
@TableName(value ="user_club")
@Data
public class UserClub implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long userClubId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 社团id
     */
    private Long clubId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    @TableLogic
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}