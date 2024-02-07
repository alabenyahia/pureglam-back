package com.pickurapps.pureglamback.repositories;

import com.pickurapps.pureglamback.entities.StaffUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StaffUserRepository extends JpaRepository<StaffUser, Long> {
    Optional<StaffUser> findFirstByEmail(String email);
}
