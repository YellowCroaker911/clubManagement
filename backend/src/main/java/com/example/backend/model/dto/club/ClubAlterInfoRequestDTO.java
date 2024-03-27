package com.example.backend.model.dto.club;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Data
public class ClubAlterInfoRequestDTO {
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,20}$",message = "索引必须是20位以内的数字")
    private String id;
    //todo:图片url
    private String avatar;
    @Pattern(regexp = "^.{0,500}$",message = "社团简介不能超过500个字符")
    private String info;
    @Pattern(regexp = "^.{0,100}$",message = "活动场地不能超过100个字符")
    private String address;
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "联系邮箱号码不符规范")
    private String contact;

}
