package com.example.backend.model.dto.activity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
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
    //todo:时间数据校验
    @NotBlank(message = "活动开始时间不能为空")
    private Date beginTime;
    @NotBlank(message = "活动结束时间不能为空")
    private Date endTime;
    @NotBlank(message = "活动地点不能为空")
    @Pattern(regexp = "^.{0,100}$",message = "活动地点不能超过100个字符")
    private String address;
    @NotBlank(message = "活动联系方式不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "联系邮箱号码不符规范")
    private String sign;
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,10}$",message = "缴费为整数单位，不得超过1亿元")
    private String money;
    @Pattern(regexp = "^.{0,500}$",message = "活动总结不能超过500个字符")
    private String summary;
}
