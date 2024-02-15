package com.pickurapps.pureglamback.dtos.customer;

import lombok.Data;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
public class CustomerStoreDto {
    private Long id;
    private String name;
    private int[] brandColor = {0, 0, 0};
    // add rating functionality private Float rating;

    private Long customerUserId;

    private List<CustomerStorePhotoDto> photos;
    private Set<CustomerStoreServiceDto> services;
}
