package com.yaremenko.demo4.controllers;

import com.yaremenko.demo4.models.User;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UserController {
    private final Map<String, String> users = new HashMap<>(); // Ім'я -> Роль

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", users);
        model.addAttribute("user", new User());
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("users", users);
            return "users";
        }
        users.put(user.getUsername(), "USER"); // Додаємо нового користувача з роллю USER
        return "redirect:/users";
    }

    @PostMapping("/delete/{username}")
    public String deleteUser(@PathVariable String username) {
        users.remove(username);
        return "redirect:/users";
    }

    @PostMapping("/update-role")
    public String updateUserRole(@RequestParam String username, @RequestParam String role) {
        if (users.containsKey(username)) {
            users.put(username, role);
        }
        return "redirect:/users";
    }
}
