package com.pickurapps.pureglamback.controllers;

import com.pickurapps.pureglamback.dtos.*;
import com.pickurapps.pureglamback.entities.AdminUser;
import com.pickurapps.pureglamback.entities.CustomerUser;
import com.pickurapps.pureglamback.entities.StaffUser;
import com.pickurapps.pureglamback.repositories.AdminUserRepository;
import com.pickurapps.pureglamback.repositories.CustomerUserRepository;
import com.pickurapps.pureglamback.repositories.StaffUserRepository;
import com.pickurapps.pureglamback.services.auth.AuthService;
import com.pickurapps.pureglamback.services.jwt.UserService;
import com.pickurapps.pureglamback.utils.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTUtil jwtUtil;

    private final CustomerUserRepository customerUserRepository;
    private final StaffUserRepository staffUserRepository;
    private final AdminUserRepository adminUserRepository;

    @PostMapping("/customer/register")
    public ResponseEntity<?> registerCustomerUser(@RequestBody RegisterCustomerRequestDto registerCustomerRequestDto) {
        if (authService.isCustomerAlreadyRegistered(registerCustomerRequestDto.getEmail())) {
            return new ResponseEntity<>("Email already used!", HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            CustomerUserDto createdCustomerUserDto = authService.createCustomerUser(registerCustomerRequestDto);
            if (createdCustomerUserDto == null) {
                return new ResponseEntity<>(
                        "Customer not created, error happened!", HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(createdCustomerUserDto, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Customer not created, error happened!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/staff/register")
    public ResponseEntity<?> registerStaffUser(@RequestBody RegisterStaffRequestDto registerStaffRequestDto) {
        if (authService.isStaffAlreadyRegistered(registerStaffRequestDto.getEmail())) {
            return new ResponseEntity<>("Email already used!", HttpStatus.NOT_ACCEPTABLE);
        }

        try {
            StaffUserDto createdStaffUserDto = authService.createStaffUser(registerStaffRequestDto);
            if (createdStaffUserDto == null) {
                return new ResponseEntity<>(
                        "Staff not created, error happened!", HttpStatus.BAD_REQUEST
                );
            }

            return new ResponseEntity<>(createdStaffUserDto, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>("Staff not created, error happened!", HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PostMapping("/admin/login")
    public LoginUserResponseDto loginAdminUser(@RequestBody LoginUserRequestDto loginUserRequestDto) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequestDto.getEmail(), loginUserRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(loginUserRequestDto.getEmail());
        Optional<AdminUser> optionalAdminUser = adminUserRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        LoginUserResponseDto loginUserResponseDto = new LoginUserResponseDto();
        if (optionalAdminUser.isPresent()) {
            loginUserResponseDto.setJwt(jwt);
            loginUserResponseDto.setUserId(optionalAdminUser.get().getId());

        }
        return loginUserResponseDto;
    }

    @PostMapping("/staff/login")
    public LoginUserResponseDto loginStaffUser(@RequestBody LoginUserRequestDto loginUserRequestDto) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequestDto.getEmail(), loginUserRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(loginUserRequestDto.getEmail());
        Optional<StaffUser> optionalStaffUser = staffUserRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        LoginUserResponseDto loginUserResponseDto = new LoginUserResponseDto();
        if (optionalStaffUser.isPresent()) {
            loginUserResponseDto.setJwt(jwt);
            loginUserResponseDto.setUserId(optionalStaffUser.get().getId());

        }
        return loginUserResponseDto;
    }

    @PostMapping("/customer/login")
    public LoginUserResponseDto loginCustomerUser(@RequestBody LoginUserRequestDto loginUserRequestDto) throws
            BadCredentialsException,
            DisabledException,
            UsernameNotFoundException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUserRequestDto.getEmail(), loginUserRequestDto.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password");
        }

        final UserDetails userDetails = userService.userDetailsService().loadUserByUsername(loginUserRequestDto.getEmail());
        Optional<CustomerUser> optionalCustomerUser = customerUserRepository.findFirstByEmail(userDetails.getUsername());
        final String jwt = jwtUtil.generateToken(userDetails);

        LoginUserResponseDto loginUserResponseDto = new LoginUserResponseDto();
        if (optionalCustomerUser.isPresent()) {
            loginUserResponseDto.setJwt(jwt);
            loginUserResponseDto.setUserId(optionalCustomerUser.get().getId());

        }
        return loginUserResponseDto;
    }
}
