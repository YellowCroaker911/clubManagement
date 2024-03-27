package com.example.backend.model.dto.club;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
@Data
public class ClubPayRequestDTO {
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,20}$",message = "索引必须是20位以内的数字")
    private String id;
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,2}$",message = "缴费为整数单位，不得超过99元")
    private String money;
}
