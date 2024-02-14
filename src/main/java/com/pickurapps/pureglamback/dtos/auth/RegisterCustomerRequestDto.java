package com.pickurapps.pureglamback.dtos.auth;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class RegisterCustomerRequestDto {
    private String email;
    private String firstName;
    private String LastName;
    private String password;

    private MultipartFile avatar;
    private byte[] returnedAvatar;
}
