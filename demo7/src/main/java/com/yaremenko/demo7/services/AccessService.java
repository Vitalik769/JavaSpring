package com.yaremenko.demo7.services;

import com.yaremenko.demo7.models.User;
import com.yaremenko.demo7.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public boolean hasAdminAccess(String username) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(user -> user.getRole() != null && user.getRole().getName().equals("ADMIN")).orElse(false);
    }

    public String checkAccess(String username, String resource) {
        Optional<User> userOpt = userRepository.findByUsername(username);

        if (userOpt.isPresent()) {
            User user = userOpt.get();
            String role = (user.getRole() != null) ? user.getRole().getName() : "НЕ ВСТАНОВЛЕНО";

            if (role.equals("ADMIN")) {
                return "✅ Адміністратор " + username + " має доступ до панелі адміністратора.";
            } else {
                return "❌ Доступ заборонено: у користувача " + username + " немає дозволу на панель адміністратора.";
            }
        }
        return "❌ Доступ заборонено: користувача " + username + " не знайдено.";
    }
}
