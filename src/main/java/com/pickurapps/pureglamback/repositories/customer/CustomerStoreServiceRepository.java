package com.pickurapps.pureglamback.repositories.customer;

import com.pickurapps.pureglamback.entities.customer.CustomerStoreService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerStoreServiceRepository extends JpaRepository<CustomerStoreService, Long> {
}
