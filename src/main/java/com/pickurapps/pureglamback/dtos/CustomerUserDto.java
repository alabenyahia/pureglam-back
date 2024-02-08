package com.pickurapps.pureglamback.dtos;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CustomerUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    private MultipartFile avatar;
    private byte[] returnedAvatar;
}
