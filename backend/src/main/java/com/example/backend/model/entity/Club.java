package com.example.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 社团
 * @TableName club
 */
@TableName(value ="club")
@Data
public class Club implements Serializable {
    /**
     * 社团id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 社团描述
     */
    private String info;

    /**
     * 活动场地
     */
    private String address;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 成员数量
     */
    private Integer member;

    /**
     * 活动数量
     */
    private Integer activity_number;

    /**
     * 公费（单位是分）
     */
    private Integer money;

    /**
     * 社长id
     */
    private Long presidentId;

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