package com.pickurapps.pureglamback.dtos.staff;

import com.pickurapps.pureglamback.enums.StaffPermissionsEnum;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@Data
public class StaffUserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private Set<StaffPermissionsEnum> permissions;

    private MultipartFile avatar;
    private byte[] returnedAvatar;
}
