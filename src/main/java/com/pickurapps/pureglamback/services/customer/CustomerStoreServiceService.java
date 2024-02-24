package com.pickurapps.pureglamback.services.customer;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;

import java.io.IOException;
import java.util.List;

public interface CustomerStoreServiceService {
    List<CustomerStoreServiceDto> getAllStoreServices(Long storeId);
    CustomerStoreServiceDto getStoreServiceById(Long storeServiceId);
    boolean addStoreService(CustomerStoreServiceDto customerStoreServiceDto, Long customerId) throws IOException;
    boolean updateStoreService(Long storeServiceId, CustomerStoreServiceDto customerStoreServiceDto) throws IOException;
    boolean deleteStoreSerice(Long storeServiceId);
}
