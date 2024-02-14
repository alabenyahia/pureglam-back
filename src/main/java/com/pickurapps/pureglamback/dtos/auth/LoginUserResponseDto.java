package com.pickurapps.pureglamback.dtos.auth;

import lombok.Data;

@Data
public class LoginUserResponseDto {
    private String jwt;
    private Long userId;
}
