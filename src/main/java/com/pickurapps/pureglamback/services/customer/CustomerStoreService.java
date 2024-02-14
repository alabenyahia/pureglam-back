package com.pickurapps.pureglamback.services.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;

import java.io.IOException;
import java.util.List;

public interface CustomerStoreService {
    List<CustomerStoreDto> getAllStoresByCustomerId(Long customerId);
    boolean addStore(CustomerStoreDto customerStoreDto) throws IOException;
    boolean updateStore(Long storeId, CustomerStoreDto customerStoreDto);
    boolean deleteStore(Long storeId);
    CustomerStoreDto getStoreById(Long storeId);
}
