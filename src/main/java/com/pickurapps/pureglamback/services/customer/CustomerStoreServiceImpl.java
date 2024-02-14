package com.pickurapps.pureglamback.services.customer;

import com.pickurapps.pureglamback.repositories.CustomerStoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerStoreServiceImpl implements CustomerStoreService{

    private final CustomerStoreRepository customerStoreRepository;
}
