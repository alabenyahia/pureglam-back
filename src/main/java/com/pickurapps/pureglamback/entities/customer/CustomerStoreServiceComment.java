package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.entities.users.CustomerUser;
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
