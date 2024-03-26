package com.example.backend.model.dto.user.account;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class UserAlterPasswordDTO {
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^.{6,20}$",message = "密码必须在6到20位之间")
    private String password;
    @NotBlank(message = "确认密码不能为空")
    @Pattern(regexp = "^.{6,20}$",message = "确认密码必须在6到20位之间")
    private String confirmedPassword;
}
