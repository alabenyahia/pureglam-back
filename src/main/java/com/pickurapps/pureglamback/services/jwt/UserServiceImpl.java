package com.pickurapps.pureglamback.services.jwt;


import com.pickurapps.pureglamback.entities.AdminUser;
import com.pickurapps.pureglamback.entities.CustomerUser;
import com.pickurapps.pureglamback.entities.StaffUser;
import com.pickurapps.pureglamback.repositories.AdminUserRepository;
import com.pickurapps.pureglamback.repositories.CustomerUserRepository;
import com.pickurapps.pureglamback.repositories.StaffUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final AdminUserRepository adminUserRepository;
    private final StaffUserRepository staffUserRepository;
    private final CustomerUserRepository customerUserRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                Optional<AdminUser> adminUser = adminUserRepository.findFirstByEmail(username);
                Optional<StaffUser> staffUser = staffUserRepository.findFirstByEmail(username);
                Optional<CustomerUser> customerUser = customerUserRepository.findFirstByEmail(username);
                if (adminUser.isPresent()) {
                    return adminUser.get();
                }
                if (staffUser.isPresent()) {
                    return staffUser.get();
                }
                if (customerUser.isPresent()) {
                    return customerUser.get();
                }
                throw new UsernameNotFoundException("User not found");
            }
        };
    }
}
