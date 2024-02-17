package com.pickurapps.pureglamback.controllers;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;
import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import com.pickurapps.pureglamback.repositories.users.CustomerUserRepository;
import com.pickurapps.pureglamback.services.customer.CustomerStoreService;
import com.pickurapps.pureglamback.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/customer/store")
@RequiredArgsConstructor
public class CustomerStoreController {
    private final CustomerStoreService customerStoreService;
    private final CustomerUserRepository customerUserRepository;
    private final JWTUtil jwtUtil;

    private static final Logger logger = LoggerFactory.getLogger(CustomerStoreController.class);



    @PostMapping("/add")
    public ResponseEntity<?> addCustomerStore(@ModelAttribute CustomerStoreDto customerStore, @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        String token = authorizationHeader.replace("Bearer ", "");
        String customerEmail = jwtUtil.extractUserName(token);
        Optional<CustomerUser> customerUser = customerUserRepository.findFirstByEmail(customerEmail);
        if (customerUser.isPresent()) {
            boolean success = customerStoreService.addStore(customerStore, customerUser.get().getId());
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();


    }

    @GetMapping("/all/bycustomer")
    public ResponseEntity<?> getAllStoreByCustomerId(@RequestHeader("Authorization") String authorizationHeader) {
        String token = authorizationHeader.replace("Bearer ", "");
        String customerEmail = jwtUtil.extractUserName(token);

        logger.info("customer email", customerEmail);
        Optional<CustomerUser> customerUser = customerUserRepository.findFirstByEmail(customerEmail);
        if (customerUser.isPresent()) {
            List<CustomerStoreDto> customerStoreDtos = customerStoreService.getAllStoresByCustomerId(customerUser.get().getId());
            return ResponseEntity.ok(customerStoreDtos);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
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
