package com.yaremenko.demo6.services;

import com.yaremenko.demo6.models.Role;
import com.yaremenko.demo6.models.User;
import com.yaremenko.demo6.repositories.RoleRepository;
import com.yaremenko.demo6.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User addUser(String username) {
        Role defaultRole = roleRepository.findByName("USER");
        if (defaultRole == null) {
            defaultRole = roleRepository.save(new Role("USER"));
        }
        User user = new User(username, defaultRole);
        return userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public void assignRole(String username, String roleName) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        if (userOpt.isPresent()) {
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = roleRepository.save(new Role(roleName));
            }
            User user = userOpt.get();
            user.setRole(role);
            userRepository.save(user);
        }
    }
}
