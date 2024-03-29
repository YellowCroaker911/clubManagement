package com.example.backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class ActivityWithClubNameVO {
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
     * 活结束时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updateTime;
}
