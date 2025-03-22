package com.yaremenko.demo7.repositories;

import com.yaremenko.demo7.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
