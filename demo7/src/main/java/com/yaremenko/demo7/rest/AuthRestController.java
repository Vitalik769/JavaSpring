package com.yaremenko.demo7.rest;

import com.yaremenko.demo7.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthRestController {
    private final UserService userService;

    public AuthRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (userService.existsByUsername(username)) {
            return ResponseEntity.badRequest().body("Користувач вже існує");
        }

        userService.addUser(username, password);
        return ResponseEntity.ok("Користувача зареєстровано");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String password = body.get("password");

        if (userService.authenticateUser(username, password)) {
            return ResponseEntity.ok("Успішний вхід");
        } else {
            return ResponseEntity.status(401).body("Невірний логін або пароль");
        }
    }
}
