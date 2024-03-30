package com.example.backend.utils.result;
import lombok.Getter;

@Getter
public enum ReturnCodes {

    SUCCESS(100,"成功"),
    VALID_FAIL(101, "数据校验错误"),
    BAD_JWT(102, "JWT错误"),
    USER_NOT_EXIST(103,"用户不存在"),
    USER_NOT_LOGIN(104,"用户名未登录"),
    EMPTY_USER_NAME(201,"用户名不能为空"),
    EMPTY_PASSWORD(202,"密码不能为空"),
    TOO_LONG_USERNAME(203,"用户名长度不能大于20"),
    TOO_LONG_PASSWORD(204,"密码长度不能大于20"),
    DIFF_PASSWORD(205,"两次输入的密码不一致"),
    EXIST_USERNAME(206,"用户名已存在"),
    DIFF_OlD_PASSWORD(207,"旧密码不正确"),
    EMPTY_CLUB_NAME(301,"社团名不能为空"),
    TOO_LONG_CLUB_NAME(302,"社团名不能大于20"),
    EXIST_CLUB_NAME(303,"社团名已存在"),
    NOT_EXIST_PRESIDENT(304,"社长账号不存在"),
    KICK_PRESIDENT(305,"社长不能踢自己"),
    NOT_PASS_YEET(306,"申请加入社团还未通过"),
    JOIN_YET(306,"已申请加入社团"),
    SIGN_UP_YET(401,"已报名活动"),
    NOT_MEMBER(402,"非社团成员"),
    NOT_SIGN_TIME(403,"不在签到时间范围内"),
    NOT_SIGN_UP(404,"未报名"),
    PAY_OR_JOIN_YET(405,"已签到或缴费"),
    INDEX_NOT_EXIST(901,"索引不存在"),
    IMG_NOTFOUND(902,"找不到图片"),
    SYSTEM_ERROR(999, "系统错误");
    private final int code;     // 状态码
    private final String message;   // 状态码信息
    ReturnCodes(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
