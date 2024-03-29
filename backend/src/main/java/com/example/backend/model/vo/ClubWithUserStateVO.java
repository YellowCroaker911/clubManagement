package com.example.backend.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ClubWithUserStateVO {

    /**
     * 社团id
     */
    private Long id;

    /**
     * 对社团缴费金额（单位是分）
     */
    private Integer contributionMoney;

    /**
     * 是否审核通过(0-审核中, 1-通过 2-未加入)
     */
    private Integer isPassed;


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
    private Integer activityNumber;

    /**
     * 公费（单位是分）
     */
    private Integer money;


    /**
     * 是否审核通过(0-审核中, 1-通过)
     */
    private Integer isAdmitted;

    /**
     * 社长id
     */
    private Long presidentId;

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
