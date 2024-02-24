package com.pickurapps.pureglamback.dtos.customer;

import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class CustomerStoreServiceDto {
    private Long id;

    private String name;
    private String description;
    private Float price;
    // add rating functionality private Float rating;

    private Date addedDate;

    private List<PhotoDto> photos;

    private Long storeId;
    private Set<CustomerStoreServiceCommentDto> comments;
}
