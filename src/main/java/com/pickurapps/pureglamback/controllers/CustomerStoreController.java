package com.pickurapps.pureglamback.controllers;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;
import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import com.pickurapps.pureglamback.services.customer.CustomerStoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/customer/store")
@RequiredArgsConstructor
public class CustomerStoreController {
    private final CustomerStoreService customerStoreService;


    @PostMapping("/")
    public ResponseEntity<?> addCustomerStore(@ModelAttribute CustomerStoreDto customerStore) throws IOException {
        boolean success = customerStoreService.addStore(customerStore);
        if (success) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<CustomerStoreDto>> getAllStoreByCustomerId(@PathVariable Long customerId) {
        List<CustomerStoreDto> customerStoreDtos = customerStoreService.getAllStoresByCustomerId(customerId);
        return ResponseEntity.ok(customerStoreDtos);
    }

    @GetMapping("/{storeId}")
    public ResponseEntity<CustomerStoreDto> getCustomerStoreById(@PathVariable Long storeId) {
        CustomerStoreDto customerStoreDto = customerStoreService.getStoreById(storeId);

        return ResponseEntity.ok(customerStoreDto);
    }

    @PutMapping("/{storeId}")
    public ResponseEntity<Void> updateCustomerStore(@PathVariable Long storeId, @ModelAttribute CustomerStoreDto customerStoreDto) throws IOException {
        try {
            boolean success = customerStoreService.updateStore(storeId, customerStoreDto);

            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteCustomerStore(@PathVariable Long storeId) {
        boolean deleted = customerStoreService.deleteStore(storeId);
        if (deleted) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }

}
