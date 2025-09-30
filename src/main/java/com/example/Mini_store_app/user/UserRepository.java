package com.example.Mini_store_app.user;

import com.example.Mini_store_app.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findFirstByEmail(String email);

    User findByUserRole(UserRole userRole);
}
