package com.example.backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserWithUserActivityStateVO implements Serializable {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 0-没缴费 1-缴费
     */
    private Integer payStatus;

    /**
     * 0-没签到 1-签到
     */
    private Integer joinStatus;

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



}
