package com.pickurapps.pureglamback.dtos.customer;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class CustomerStorePhotoDto {
    private Long id;
    private MultipartFile photo;
    private byte[] returnedPhoto;
}
