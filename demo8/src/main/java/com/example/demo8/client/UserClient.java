package com.example.demo8.client;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Map;

@Component
public class UserClient {
    private final WebClient webClient;

    public UserClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8080").build();
    }

    // Отримати всіх користувачів у вигляді JSON (як текст)
    public Mono<String> getUsers() {
        return webClient.get()
                .uri("/api/users")
                .retrieve()
                .bodyToMono(String.class);
    }

    // Отримати користувача за ID
    public Mono<String> getUserById(Long id) {
        return webClient.get()
                .uri("/api/users/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }

    // Додати користувача
    public Mono<String> addUser(String username, String password, String role) {
        return webClient.post()
                .uri("/api/users/add")
                .bodyValue(Map.of("username", username, "password", password, "role", role))
                .retrieve()
                .bodyToMono(String.class);
    }

    // Оновити користувача
    public Mono<String> updateUser(Long id, String username, String role) {
        return webClient.post()
                .uri("/api/users/update")
                .bodyValue(Map.of("id", id.toString(), "username", username, "role", role))
                .retrieve()
                .bodyToMono(String.class);
    }

    // Видалити користувача
    public Mono<String> deleteUser(Long id) {
        return webClient.post()
                .uri("/api/users/delete/{id}", id)
                .retrieve()
                .bodyToMono(String.class);
    }

    // Отримати користувачів з ролями
    public Mono<String> getUsersWithRoles() {
        return webClient.get()
                .uri("/api/access/users")
                .retrieve()
                .bodyToMono(String.class);
    }

    // Призначити роль
    public Mono<String> assignRole(String username, String role) {
        return webClient.post()
                .uri("/api/access/assign-role")
                .bodyValue(Map.of("username", username, "role", role))
                .retrieve()
                .bodyToMono(String.class);
    }

    // Перевірити доступ
    public Mono<String> checkAccess(String username, String resource) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/api/access/check-access")
                        .queryParam("username", username)
                        .queryParam("resource", resource)
                        .build())
                .retrieve()
                .bodyToMono(String.class);
    }
    public Mono<String> register(String username, String password) {
        return webClient.post()
                .uri("/api/auth/register")
                .bodyValue(Map.of("username", username, "password", password))
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> login(String username, String password) {
        return webClient.post()
                .uri("/api/auth/login")
                .bodyValue(Map.of("username", username, "password", password))
                .retrieve()
                .bodyToMono(String.class);
    }

}
