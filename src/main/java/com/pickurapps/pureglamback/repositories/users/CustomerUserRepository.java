package com.pickurapps.pureglamback.repositories.users;

import com.pickurapps.pureglamback.entities.users.CustomerUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerUserRepository extends JpaRepository<CustomerUser, Long> {
    Optional<CustomerUser> findFirstByEmail(String email);
}
