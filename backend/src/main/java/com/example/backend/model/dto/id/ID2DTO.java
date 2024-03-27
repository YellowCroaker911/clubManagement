package com.example.backend.model.dto.id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ID2DTO {
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,20}$",message = "索引必须是20位以内的数字")
    private String id1;
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,20}$",message = "索引必须是20位以内的数字")
    private String id2;
}