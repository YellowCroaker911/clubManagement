package com.example.backend.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 活动
 * @TableName activity
 */
@TableName(value ="activity")
@Data
public class Activity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;
    /**
     * 所属社团id
     */
    private Long clubId;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动信息
     */
    private String info;

    /**
     * 活动主题
     */
    private String title;

    /**
     * 活动开始时间
     */
    private Date beginTime;

    /**
     * 活结束时间
     */
    private Date endTime;

    /**
     * 活动地点
     */
    private String address;

    /**
     * 报名方式
     */
    private String sign;

    /**
     * 活动缴费
     */

    private Long money;

    /**
     * 参加人数
     */
    private Integer joinPeople;

    /**
     * 签到人数
     */
    private Integer attendancePeople;

    /**
     * 活动总结
     */
    private String summary;

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