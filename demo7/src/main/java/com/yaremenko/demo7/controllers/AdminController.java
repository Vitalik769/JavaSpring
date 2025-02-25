package com.yaremenko.demo7.controllers;

import com.yaremenko.demo7.services.AccessService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final AccessService accessService;

    public AdminController(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping("/check-access")
    public String checkAccess(@RequestParam String username, Model model) {
        if (accessService.hasAdminAccess(username)) {
            return "redirect:/admin";
        } else {
            model.addAttribute("message", accessService.checkAccess(username, "admin"));
            return "access-denied";
        }
    }

    @GetMapping
    public String showAdminPanel(Model model) {
        model.addAttribute("message", "Ласкаво просимо в панель адміністратора!");
        return "admin";
    }
}
