package com.pickurapps.pureglamback.dtos.auth;

import lombok.Data;

@Data
public class LoginUserRequestDto {
    private String email;
    private String password;
}
