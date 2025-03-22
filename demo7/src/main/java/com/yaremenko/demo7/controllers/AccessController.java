package com.yaremenko.demo7.controllers;

import com.yaremenko.demo7.services.AccessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/access")
public class AccessController {
    private final AccessService accessService;

    public AccessController(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping
    public String showAccessPage(Model model) {
        model.addAttribute("users", accessService.getAllUsersWithRoles()); // Передача користувачів у модель
        return "access";
    }

    @PostMapping("/assign-role")
    public String assignRole(@RequestParam String username, @RequestParam String role) {
        accessService.assignRole(username, role);
        return "redirect:/access";
    }

}
