package com.yaremenko.demo7.services;

import com.yaremenko.demo7.models.Role;
import com.yaremenko.demo7.models.User;
import com.yaremenko.demo7.repositories.RoleRepository;
import com.yaremenko.demo7.repositories.UserRepository;
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

    // Метод для додавання користувача з роллю
    public User addUser(String username, String password, String roleName) {
        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            role = roleRepository.save(new Role(roleName)); // Якщо ролі немає, створюємо її
        }
        User user = new User(username, password, role);
        return userRepository.save(user);
    }

    // Перевантажений метод для виклику без ролі (буде присвоєна роль "USER" за замовчуванням)
    public User addUser(String username, String password) {
        return addUser(username, password, "USER");
    }


    // Метод для оновлення даних користувача
    public void updateUser(Long id, String username, String roleName) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            user.setUsername(username);
            Role role = roleRepository.findByName(roleName);
            if (role == null) {
                role = roleRepository.save(new Role(roleName));
            }
            user.setRole(role);
            userRepository.save(user);
        }
    }

    public boolean authenticateUser(String username, String password) {
        Optional<User> userOpt = userRepository.findByUsername(username);
        return userOpt.map(user -> user.getPassword().equals(password)).orElse(false);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public boolean existsByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
