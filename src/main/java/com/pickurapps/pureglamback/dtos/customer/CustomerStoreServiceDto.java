package com.pickurapps.pureglamback.dtos.customer;

import com.pickurapps.pureglamback.entities.customer.CustomerStoreServiceComment;
import lombok.Data;

import java.util.Collections;
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

    private Long store;
    private Set<CustomerStoreServiceCommentDto> comments;
}
