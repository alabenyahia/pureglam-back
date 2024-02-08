package com.pickurapps.pureglamback.services.auth;

import com.pickurapps.pureglamback.dtos.CustomerUserDto;
import com.pickurapps.pureglamback.dtos.RegisterCustomerRequestDto;
import com.pickurapps.pureglamback.dtos.RegisterStaffRequestDto;
import com.pickurapps.pureglamback.dtos.StaffUserDto;

import java.io.IOException;

public interface AuthService {
    CustomerUserDto createCustomerUser(RegisterCustomerRequestDto registerCustomerRequestDto) throws IOException;
    StaffUserDto createStaffUser(RegisterStaffRequestDto registerStaffRequestDto) throws IOException;

    boolean isCustomerAlreadyRegistered(String email);
    boolean isStaffAlreadyRegistered(String email);
}
