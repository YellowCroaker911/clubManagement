package com.example.backend.model.dto.club;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
public class ClubRegisterRequestDTO implements Serializable {
    @NotBlank(message = "社团名不能为空")
    @Pattern(regexp = "^[a-zA-Z0-9\\u4e00-\\u9fff]{1,20}$",message = "社团名只能包含中英文字符和数字，不能超过20位")
    private String name;
}
