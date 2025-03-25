package com.yaremenko.demo7.controllers;

import com.yaremenko.demo7.models.User;
import com.yaremenko.demo7.services.AccessService;
import com.yaremenko.demo7.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AccessService accessService;
    private final UserService userService;

    public AdminController(AccessService accessService, UserService userService) {
        this.accessService = accessService;
        this.userService = userService;
    }

    @GetMapping
    public String showAdminPanel(Model model) {
        List<User> users = userService.getAllUsers(); // Отримуємо всіх користувачів із бази
        model.addAttribute("users", users);
        model.addAttribute("message", "Ласкаво просимо в панель адміністратора!");
        return "admin";
    }

    // Метод для призначення ролей
    @PostMapping("/assign-role")
    public String assignRole(@RequestParam String username, @RequestParam String role) {
        accessService.assignRole(username, role);
        return "redirect:/admin";
    }

    // Метод для видалення користувача
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }
}
