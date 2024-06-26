package com.example.backend.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

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
     * 对社团缴费金额（单位是分）
     */
    private Integer contributionMoney;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    /**
     * 是否审核通过(0-审核中, 1-通过)
     */
    private Integer isPassed;

//    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}