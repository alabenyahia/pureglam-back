package com.pickurapps.pureglamback.services.auth;

import com.pickurapps.pureglamback.dtos.customer.CustomerUserDto;
import com.pickurapps.pureglamback.dtos.auth.RegisterCustomerRequestDto;
import com.pickurapps.pureglamback.dtos.auth.RegisterStaffRequestDto;
import com.pickurapps.pureglamback.dtos.staff.StaffUserDto;
import com.pickurapps.pureglamback.entities.users.AdminUser;
import com.pickurapps.pureglamback.entities.users.CustomerUser;
import com.pickurapps.pureglamback.entities.users.StaffUser;
import com.pickurapps.pureglamback.repositories.users.AdminUserRepository;
import com.pickurapps.pureglamback.repositories.users.CustomerUserRepository;
import com.pickurapps.pureglamback.repositories.users.StaffUserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final CustomerUserRepository customerUserRepository;
    private final StaffUserRepository staffUserRepository;
    private final AdminUserRepository adminUserRepository;

    @PostConstruct
    public void createAdminAccount() {
        if (adminUserRepository.count() == 0) {
            AdminUser newAdminAccount = new AdminUser();
            newAdminAccount.setFirstName("SUPER");
            newAdminAccount.setLastName("ADMIN");
            newAdminAccount.setEmail("admin@admin.com");
            newAdminAccount.setPassword(new BCryptPasswordEncoder().encode("admin"));
            adminUserRepository.save(newAdminAccount);
            System.out.println("Admin account created successfully!");
        }
    }

    @Override
    public CustomerUserDto createCustomerUser(RegisterCustomerRequestDto registerCustomerRequestDto) throws IOException {
        try {
            CustomerUser customerUser = new CustomerUser();
            customerUser.setFirstName(registerCustomerRequestDto.getFirstName());
            customerUser.setLastName(registerCustomerRequestDto.getLastName());
            customerUser.setEmail(registerCustomerRequestDto.getEmail());
            customerUser.setPassword(new BCryptPasswordEncoder().encode(registerCustomerRequestDto.getPassword()));

            if (registerCustomerRequestDto.getAvatar() == null) {
                customerUser.setAvatar(null);
            } else {
                customerUser.setAvatar(registerCustomerRequestDto.getAvatar().getBytes());
            }

            CustomerUser createdCustomerUser = customerUserRepository.save(customerUser);
            CustomerUserDto customerUserDto = new CustomerUserDto();
            customerUserDto.setId(createdCustomerUser.getId());

            return customerUserDto;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public StaffUserDto createStaffUser(RegisterStaffRequestDto registerStaffRequestDto)  throws IOException {
        try {
            StaffUser staffUser = new StaffUser();
            staffUser.setFirstName(registerStaffRequestDto.getFirstName());
            staffUser.setLastName(registerStaffRequestDto.getLastName());
            staffUser.setEmail(registerStaffRequestDto.getEmail());
            staffUser.setPassword(new BCryptPasswordEncoder().encode(registerStaffRequestDto.getPassword()));

            if (registerStaffRequestDto.getAvatar() == null) {
                staffUser.setAvatar(null);
            } else {
                staffUser.setAvatar(registerStaffRequestDto.getAvatar().getBytes());
            }

            staffUser.setPermissions(registerStaffRequestDto.getPermissions());

            StaffUser createdStaffUser = staffUserRepository.save(staffUser);
            StaffUserDto staffUserDto = new StaffUserDto();
            staffUserDto.setId(createdStaffUser.getId());

            return staffUserDto;
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @Override
    public boolean isCustomerAlreadyRegistered(String email) {
        return customerUserRepository.findFirstByEmail(email).isPresent();
    }

    @Override
    public boolean isStaffAlreadyRegistered(String email) {
        return staffUserRepository.findFirstByEmail(email).isPresent();
    }
}
