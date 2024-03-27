package com.example.backend.model.dto.user;

import lombok.Data;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class UserAlterInfoRequestDTO implements Serializable {
    // 修改信息可以为空
    @Pattern(regexp = "^()|([a-zA-Z0-9\\u4e00-\\u9fff]{1,20})$",message = "昵称只能包含中英文字符和数字，不能超过20位")
    private String name;
    @Pattern(regexp = "^()|([01])$", message = "性别必须为0或1")
    private String gender;
    @Pattern(regexp = "^()|(1[3-9]\\d{9})$",message = "手机号码不符规范")
    private String phone;
    @Pattern(regexp = "^()|([a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$", message = "邮箱号码不符规范")
    private String email;
}
