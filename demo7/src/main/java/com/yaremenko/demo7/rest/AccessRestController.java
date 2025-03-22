package com.yaremenko.demo7.rest;

import com.yaremenko.demo7.models.User;
import com.yaremenko.demo7.services.AccessService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/access")
public class AccessRestController {
    private final AccessService accessService;

    public AccessRestController(AccessService accessService) {
        this.accessService = accessService;
    }

    @GetMapping("/users")
    public List<User> getAllUsersWithRoles() {
        return accessService.getAllUsersWithRoles();
    }

    @PostMapping("/assign-role")
    public ResponseEntity<String> assignRole(@RequestBody Map<String, String> body) {
        String username = body.get("username");
        String role = body.get("role");
        accessService.assignRole(username, role);
        return ResponseEntity.ok("Роль призначено");
    }

    @GetMapping("/check-access")
    public String checkAccess(@RequestParam String username, @RequestParam String resource) {
        return accessService.checkAccess(username, resource);
    }
}
