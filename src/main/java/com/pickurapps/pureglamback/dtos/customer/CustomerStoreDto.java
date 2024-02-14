package com.pickurapps.pureglamback.dtos.customer;

import lombok.Data;

import java.awt.Color;
import java.util.Collections;
import java.util.List;
import java.util.Set;

@Data
public class CustomerStoreDto {
    private Long id;
    private String name;
    private Color brandColor;
    private Float rating;

    private List<CustomerStorePhotoDto> photos = Collections.emptyList();
    private Set<CustomerStoreServiceDto> services = Collections.emptySet();
}
