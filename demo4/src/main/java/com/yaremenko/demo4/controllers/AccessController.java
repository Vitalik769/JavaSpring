package com.yaremenko.demo4.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/access")
public class AccessController {
    private final Map<String, String> userRoles = new HashMap<>();

    @GetMapping
    public String showAccessPage(Model model) {
        model.addAttribute("userRoles", userRoles);
        return "access";
    }

    @PostMapping("/assign-role")
    public String assignRole(@RequestParam String username, @RequestParam String role) {
        userRoles.put(username, role);
        return "redirect:/access";
    }
}
