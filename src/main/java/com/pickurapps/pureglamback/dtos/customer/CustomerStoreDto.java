package com.pickurapps.pureglamback.dtos.customer;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class CustomerStoreDto {
    private Long id;
    private String name;
    private String brandColor;
    // add rating functionality private Float rating;

    private Long customerUserId;

    private List<PhotoDto> photos;
    private Set<CustomerStoreServiceDto> services;
}
