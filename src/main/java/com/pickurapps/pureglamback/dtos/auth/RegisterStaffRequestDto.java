package com.pickurapps.pureglamback.dtos.auth;

import com.pickurapps.pureglamback.enums.StaffPermissionsEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class RegisterStaffRequestDto {
    private String email;
    private String firstName;
    private String LastName;
    private String password;
    private Set<StaffPermissionsEnum> permissions;

    private MultipartFile avatar;
    private byte[] returnedAvatar;
}
