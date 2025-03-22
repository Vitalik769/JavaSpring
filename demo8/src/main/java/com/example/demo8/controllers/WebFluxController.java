package com.example.demo8.controllers;

import com.example.demo8.client.UserClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/webflux")
public class WebFluxController {
    private final UserClient userClient;

    public WebFluxController(UserClient userClient) {
        this.userClient = userClient;
    }

    @GetMapping("/api/users")
    public Mono<String> getUsers() {
        return userClient.getUsers()
                .onErrorReturn("⚠️ Сталася помилка при отриманні користувачів");
    }

    @GetMapping("/api/users/{id}")
    public Mono<String> getUserById(@PathVariable Long id) {
        return userClient.getUserById(id);
    }

    @PostMapping("/api/users/add")
    public Mono<String> addUser(@RequestBody Map<String, String> requestBody) {
        String username = requestBody.get("username");
        String password = requestBody.get("password");
        String role = requestBody.get("role");
        return userClient.addUser(username, password, role);
    }

    @PostMapping("/api/users/update")
    public Mono<String> updateUser(@RequestBody Map<String, String> requestBody) {
        return userClient.updateUser(
                Long.valueOf(requestBody.get("id")),
                requestBody.get("username"),
                requestBody.get("role")
        );
    }

    @PostMapping("/api/users/delete/{id}")
    public Mono<String> deleteUser(@PathVariable Long id) {
        return userClient.deleteUser(id);
    }

    @GetMapping("/api/access/users")
    public Mono<String> getUsersWithRoles() {
        return userClient.getUsersWithRoles();
    }

    @PostMapping("/api/access/assign-role")
    public Mono<String> assignRole(@RequestBody Map<String, String> requestBody) {
        return userClient.assignRole(requestBody.get("username"), requestBody.get("role"));
    }

    @GetMapping("/api/access/check-access")
    public Mono<String> checkAccess(@RequestParam String username, @RequestParam String resource) {
        return userClient.checkAccess(username, resource);
    }

    @PostMapping("/api/auth/register")
    public Mono<String> register(@RequestBody Map<String, String> requestBody) {
        return userClient.register(requestBody.get("username"), requestBody.get("password"));
    }

    @PostMapping("/api/auth/login")
    public Mono<String> login(@RequestBody Map<String, String> requestBody) {
        return userClient.login(requestBody.get("username"), requestBody.get("password"));
    }


}
