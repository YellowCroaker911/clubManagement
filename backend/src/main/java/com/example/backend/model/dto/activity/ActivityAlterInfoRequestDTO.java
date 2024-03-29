package com.example.backend.model.dto.activity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.Date;
@Data
public class ActivityAlterInfoRequestDTO {
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,17}$",message = "索引必须是17位以内的数字")
    private String id;
    @NotBlank(message = "活动名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fff]{1,20}$",message = "活动名只能包含中英文字符和数字，不能超过20位")
    private String name;
    @NotBlank(message = "活动信息不能为空")
    @Pattern(regexp = "^.{0,500}$",message = "活动信息不能超过500个字符")
    private String info;
    @NotBlank(message = "活动主题不能为空")
    @Pattern(regexp = "^.{0,50}$",message = "活动主题不能超过50个字符")
    private String title;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date beginTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date endTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date signBeginTime;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date signEndTime;
    @NotBlank(message = "活动地点不能为空")
    @Pattern(regexp = "^.{0,100}$",message = "活动地点不能超过100个字符")
    private String address;
    @NotBlank(message = "活动联系方式不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "联系邮箱号码不符规范")
    private String sign;
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,10}$",message = "缴费为整数单位，不得超过1亿元")
    private String money;
    @Pattern(regexp = "^()|.{0,500}$",message = "活动总结不能超过500个字符")
    private String summary;
}
