package com.yaremenko.demo7.config;

import com.yaremenko.demo7.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/register", "/login").permitAll()
                        .requestMatchers("/admin").hasRole("ADMIN") // Доступ тільки для адміністратора
                        .requestMatchers("/users/add", "/users/update", "/users/delete/**").hasRole("ADMIN")
                        //.requestMatchers("/access/assign-role").hasRole("ADMIN")
                        .requestMatchers("/access/assign-role").hasRole("ADMIN")
                        .requestMatchers("/users", "/access").authenticated()
                        .requestMatchers("/users").permitAll()
                        .requestMatchers("/api/**").permitAll()
                        .requestMatchers("/ws/**").permitAll()
                        .requestMatchers("/favicon.ico").permitAll()




                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .defaultSuccessUrl("/users", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login")
                        .permitAll()
                )
                .csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> userRepository.findByUsername(username)
                .map(user -> org.springframework.security.core.userdetails.User
                        .withUsername(user.getUsername())
                        .password("{noop}" + user.getPassword())
                        .roles(user.getRole().getName()) // Використовуємо роль користувача
                        .build())
                .orElseThrow(() -> new RuntimeException("Користувача не знайдено"));
    }
}
