package com.yaremenko.demo6.repositories;

import com.yaremenko.demo6.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.role = (SELECT r FROM Role r WHERE r.name = :role) WHERE u.username = :username")
    void assignUserRole(String username, String role);
}
