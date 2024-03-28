package com.example.backend.model.dto.id;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class Id2DTO {
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,17}$",message = "索引必须是17位以内的数字")
    private String id1;
    @NotBlank(message = "索引不能为空")
    @Pattern(regexp = "^\\d{1,17}$",message = "索引必须是17位以内的数字")
    private String id2;
}
