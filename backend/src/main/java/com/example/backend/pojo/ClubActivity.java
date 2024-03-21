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
 * 社团和活动关系表
 * @TableName club_activity
 */
@TableName(value ="club_activity")
@Data
@NoArgsConstructor
@AllArgsConstructor
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