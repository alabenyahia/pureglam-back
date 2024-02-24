package com.pickurapps.pureglamback.dtos.customer;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class PhotoDto {
    private Long id;
    private MultipartFile photo;
    private byte[] returnedPhoto;
}
