package com.pickurapps.pureglamback.repositories.customer;

import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStoreRepository extends JpaRepository<CustomerStore, Long> {
}
