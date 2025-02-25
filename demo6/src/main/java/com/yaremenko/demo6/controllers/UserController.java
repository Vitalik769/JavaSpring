package com.yaremenko.demo6.controllers;

import com.yaremenko.demo6.models.User;
import com.yaremenko.demo6.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String showUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        model.addAttribute("user", new User()); // Виправлення: додаємо пустого юзера для форми
        return "users";
    }

    @PostMapping("/add")
    public String addUser(@RequestParam String username) {
        userService.addUser(username);
        return "redirect:/users";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}
