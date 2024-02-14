package com.pickurapps.pureglamback.dtos.customer;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerStoreServiceCommentDto {
    private Long id;
    private Long userId;
    private String comment;
    private Date addedDate;
}
