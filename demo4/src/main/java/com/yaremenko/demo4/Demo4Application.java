package com.yaremenko.demo4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.yaremenko.demo4.repository")
@EntityScan(basePackages = "com.yaremenko.demo4.model") // Вказуємо пакет з моделями
public class Demo4Application {
	public static void main(String[] args) {
		SpringApplication.run(Demo4Application.class, args);
	}
}
