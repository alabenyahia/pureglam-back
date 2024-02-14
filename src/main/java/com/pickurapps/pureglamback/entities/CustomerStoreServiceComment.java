package com.pickurapps.pureglamback.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "customer_store_service_comment")
public class CustomerStoreServiceComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_user_id", nullable = false)
    private CustomerUser customerUser;

    private String comment;
    private Date addedDate;
}
