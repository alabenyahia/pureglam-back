package com.pickurapps.pureglamback.repositories.customer;

import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerStoreRepository extends JpaRepository<CustomerStore, Long> {
    List<CustomerStore> findAllByCustomerUserId(Long customerId);
}
