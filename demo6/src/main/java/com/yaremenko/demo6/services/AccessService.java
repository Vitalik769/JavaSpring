package com.yaremenko.demo6.services;

import com.yaremenko.demo6.models.User;
import com.yaremenko.demo6.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccessService {
    private final UserRepository userRepository;

    public AccessService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void assignRole(String username, String role) {
        userRepository.assignUserRole(username, role);
    }

    public List<User> getAllUsersWithRoles() {
        return userRepository.findAll();
    }
}
