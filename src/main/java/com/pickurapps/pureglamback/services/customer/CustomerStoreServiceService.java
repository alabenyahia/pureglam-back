package com.pickurapps.pureglamback.services.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;

import java.util.List;

public interface CustomerStoreServiceService {
    List<CustomerStoreServiceDto> getAllStoreServices(Long storeId);
    CustomerStoreServiceDto getStoreServiceById(Long storeServiceId);
    boolean addStoreService(CustomerStoreServiceDto customerStoreServiceDto, Long customerId);
    boolean updateStoreService(Long storeServiceId, CustomerStoreServiceDto customerStoreServiceDto);
    boolean deleteStoreSerice(Long storeServiceId);
}
