package com.pickurapps.pureglamback.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Data
@Table(name = "customer_store")
public class CustomerStore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Color brandColor;
    private Float rating;

    @OneToMany(mappedBy = "customer_store", cascade = CascadeType.ALL)
    private List<CustomerStorePhoto> photos = new ArrayList<>();


    @OneToMany(mappedBy = "customer_store", cascade = CascadeType.ALL)
    private Set<CustomerStoreService> services;

    //TODO: ADD [lat, long] for store's map location

}
