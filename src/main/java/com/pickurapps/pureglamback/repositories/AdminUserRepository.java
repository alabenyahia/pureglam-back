package com.pickurapps.pureglamback.repositories;

import com.pickurapps.pureglamback.entities.users.AdminUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminUserRepository extends JpaRepository<AdminUser, Long> {
    Optional<AdminUser> findFirstByEmail(String email);
}
