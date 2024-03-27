package com.example.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社团和活动关系表
 * @TableName club_activity
 */
@TableName(value ="club_activity")
@Data
public class ClubActivity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long clubActivityId;

    /**
     * 社团id
     */
    private Long clubId;

    /**
     * 活动id
     */
    private Long activityId;

    /**
     * 社团对该活动的花费（单位是分）
     */
    private Integer expense;

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