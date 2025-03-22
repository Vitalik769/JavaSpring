package com.yaremenko.demo7.rest;

import com.yaremenko.demo7.models.User;
import com.yaremenko.demo7.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    // ✅ Отримати всіх користувачів
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }


    // ✅ Додати користувача
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody Map<String, String> body) {
        try {
            String username = body.get("username");
            String password = body.get("password");
            String role = body.get("role");
            User user = userService.addUser(username, password, role);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // ✅ Оновити користувача
    @PostMapping("/update")
    public ResponseEntity<String> updateUser(@RequestBody Map<String, String> body) {
        try {
            Long id = Long.valueOf(body.get("id"));
            String username = body.get("username");
            String role = body.get("role");
            userService.updateUser(id, username, role);
            return ResponseEntity.ok("Користувача оновлено");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Помилка оновлення: " + e.getMessage());
        }
    }

    // ✅ Видалити користувача
    @PostMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return ResponseEntity.ok("Користувача видалено");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Помилка видалення: " + e.getMessage());
        }
    }
}
