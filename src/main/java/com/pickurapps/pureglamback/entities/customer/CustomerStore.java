package com.pickurapps.pureglamback.entities.customer;

import com.pickurapps.pureglamback.entities.users.CustomerUser;
import jakarta.persistence.*;
import lombok.Data;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
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
    // add rating functionality private Float rating;

    @OneToMany(cascade = CascadeType.ALL)
    private List<CustomerStorePhoto> photos = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_user_id", nullable = false)
    private CustomerUser customerUser;

    @OneToMany(mappedBy = "customer_store", cascade = CascadeType.ALL)
    private Set<CustomerStoreService> services = Collections.emptySet();

    //TODO: ADD [lat, long] for store's map location

}
