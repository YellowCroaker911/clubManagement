package com.example.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社员和活动关系表
 * @TableName user_activity
 */
@TableName(value ="user_activity")
@Data
public class UserActivity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long userActivityId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 活动id
     */
    private Long activityId;


    /**
     * 0-没缴费 1-缴费
     */
    private Integer payStatus;

    /**
     * 0-没签到 1-签到
     */
    private Integer joinStatus;

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