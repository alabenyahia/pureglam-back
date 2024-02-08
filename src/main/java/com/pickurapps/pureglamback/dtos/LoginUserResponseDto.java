package com.pickurapps.pureglamback.dtos;

import lombok.Data;

@Data
public class LoginUserResponseDto {
    private String jwt;
    private Long userId;
}
