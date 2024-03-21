package com.example.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 活动
 * @TableName activity
 */
@TableName(value ="activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Activity implements Serializable {
    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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
     * 活动时间
     */
    private Date time;

    /**
     * 活动地点
     */
    private String address;

    /**
     * 报名方式
     */
    private String sign;

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
    private Integer isDelete;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}