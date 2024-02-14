package com.pickurapps.pureglamback.services.auth;

import com.pickurapps.pureglamback.dtos.customer.CustomerUserDto;
import com.pickurapps.pureglamback.dtos.auth.RegisterCustomerRequestDto;
import com.pickurapps.pureglamback.dtos.auth.RegisterStaffRequestDto;
import com.pickurapps.pureglamback.dtos.staff.StaffUserDto;

import java.io.IOException;

public interface AuthService {
    CustomerUserDto createCustomerUser(RegisterCustomerRequestDto registerCustomerRequestDto) throws IOException;
    StaffUserDto createStaffUser(RegisterStaffRequestDto registerStaffRequestDto) throws IOException;

    boolean isCustomerAlreadyRegistered(String email);
    boolean isStaffAlreadyRegistered(String email);
}
