package com.example.backend.model.dto.club;

import lombok.Data;

import java.io.Serializable;

@Data
public class ClubRegisterRequestDTO implements Serializable {
    private String name;
    private String president_id;
}
