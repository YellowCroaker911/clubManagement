package com.example.backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;
@Data
public class UserActivityExtendVO {
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;

    /**
     * 所属社团id
     */
    private Long clubId;

    /**
     * 所属社团name
     */
    private String clubName;

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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;

    /**
     * 活动结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;

    /**
     * 签到开始时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date signBeginTime;

    /**
     * 签到结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date signEndTime;

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
}
