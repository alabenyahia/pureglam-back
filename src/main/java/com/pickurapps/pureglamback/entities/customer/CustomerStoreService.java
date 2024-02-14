package com.pickurapps.pureglamback.entities.customer;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collections;
import java.util.Date;
import java.util.Set;

@Entity
@Data
@Table(name = "customer_store_service")
public class CustomerStoreService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private Float price;
    // add rating functionality private Float rating;
    private Date addedDate;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<CustomerStoreServiceComment> comments = Collections.emptySet();

    @ManyToOne
    private CustomerStore store;

}
