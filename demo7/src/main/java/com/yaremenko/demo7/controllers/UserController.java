package com.yaremenko.demo7.controllers;

import com.yaremenko.demo7.services.UserService;
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
        return "users";
    }

    // ✅ Оновлення користувача (зміна імені або ролі)
    @PostMapping("/update")
    public String updateUser(@RequestParam Long id, @RequestParam String username, @RequestParam String role) {
        userService.updateUser(id, username, role);
        return "redirect:/users";
    }

    // ✅ Додавання користувача адміністратором
    @PostMapping("/add")
    public String addUser(@RequestParam String username, @RequestParam String password, @RequestParam String role) {
        userService.addUser(username, password, role);
        return "redirect:/users";
    }

    // ✅ Видалення користувача за ID
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }
}


