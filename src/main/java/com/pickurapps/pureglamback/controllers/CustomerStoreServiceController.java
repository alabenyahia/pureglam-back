package com.pickurapps.pureglamback.controllers;

import com.pickurapps.pureglamback.dtos.customer.CustomerStoreDto;
import com.pickurapps.pureglamback.dtos.customer.CustomerStoreServiceDto;
import com.pickurapps.pureglamback.entities.customer.CustomerStore;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import com.pickurapps.pureglamback.repositories.customer.CustomerStoreRepository;
import com.pickurapps.pureglamback.repositories.users.CustomerUserRepository;
import com.pickurapps.pureglamback.services.customer.CustomerStoreServiceService;
import com.pickurapps.pureglamback.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer/store/service")
public class CustomerStoreServiceController {
    private final CustomerStoreServiceService customerStoreServiceService;
    private final CustomerStoreRepository customerStoreRepository;
    private final CustomerUserRepository customerUserRepository;
    private final JWTUtil jwtUtil;

    @PostMapping("/add")
    public ResponseEntity<?> addCustomerStoreService(@ModelAttribute CustomerStoreServiceDto customerStoreServiceDto, @RequestHeader("Authorization") String authorizationHeader) throws IOException {
        String token = authorizationHeader.replace("Bearer ", "");
        String customerEmail = jwtUtil.extractUserName(token);
        Optional<CustomerUser> customerUser = customerUserRepository.findFirstByEmail(customerEmail);
        if (customerUser.isPresent()) {
            boolean success = customerStoreServiceService.addStoreService(customerStoreServiceDto, customerUser.get().getId());
            if (success) {
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/all/bystore/{storeId}")
    public ResponseEntity<?> getAllServicesByStoreId(@RequestHeader("Authorization") String authorizationHeader, @PathVariable Long storeId) {
        String token = authorizationHeader.replace("Bearer ", "");
        String customerEmail = jwtUtil.extractUserName(token);


        Optional<CustomerUser> customerUser = customerUserRepository.findFirstByEmail(customerEmail);
        Optional<CustomerStore> customerStore = customerStoreRepository.findById(storeId);
        if (customerStore.isPresent() && customerUser.isPresent()) {
            if (Objects.equals(customerStore.get().getCustomerUser().getId(), customerUser.get().getId())) {
                List<CustomerStoreServiceDto> customerStoreServiceDtos = customerStoreServiceService.getAllStoreServices(storeId);
                return ResponseEntity.ok(customerStoreServiceDtos);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{storeServiceId}")
    public ResponseEntity<CustomerStoreServiceDto> getCustomerStoreServiceById(@PathVariable Long storeServiceId) {
        CustomerStoreServiceDto customerStoreServiceDto = customerStoreServiceService.getStoreServiceById(storeServiceId);

        return ResponseEntity.ok(customerStoreServiceDto);
    }

    @PutMapping("/{storeServiceId}")
    public ResponseEntity<Void> updateCustomerStoreService(@PathVariable Long storeServiceId, @ModelAttribute CustomerStoreServiceDto customerStoreServiceDto) throws IOException {
        try {
            boolean success = customerStoreServiceService.updateStoreService(storeServiceId, customerStoreServiceDto);

            if (success) return ResponseEntity.status(HttpStatus.OK).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{storeServiceId}")
    public ResponseEntity<Void> deleteCustomerStoreService(@PathVariable Long storeServiceId) {
        boolean deleted = customerStoreServiceService.deleteStoreSerice(storeServiceId);
        if (deleted) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.notFound().build();
    }
}
