package com.example.backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class UserClubExtendUserVO {
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
    private Integer contribution_money;

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
     * 名称
     */
    private String username;

    /**
     * 名称
     */
    private String name;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 电话
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 贡献金额（单位是分）
     */
    private Integer money;
}
