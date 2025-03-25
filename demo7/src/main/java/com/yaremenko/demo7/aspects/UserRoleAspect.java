package com.yaremenko.demo7.aspects;

import com.yaremenko.demo7.models.Role;
import com.yaremenko.demo7.models.User;
import com.yaremenko.demo7.repositories.RoleRepository;
import com.yaremenko.demo7.services.WebSocketSender;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.logging.Logger;

@Aspect
@Component
public class UserRoleAspect {
    private final RoleRepository roleRepository;
    private final WebSocketSender webSocketSender;

    public UserRoleAspect(RoleRepository roleRepository, WebSocketSender webSocketSender) {
        this.roleRepository = roleRepository;
        this.webSocketSender = webSocketSender;
    }

    @Around("execution(* com.yaremenko.demo7.repositories.UserRepository.save(..)) && args(user,..)")
    @Transactional
    public Object setDefaultRoleIfNotAssigned(ProceedingJoinPoint joinPoint, User user) throws Throwable {
        String info;
        if (user.getRole() == null) {
            Role defaultRole = roleRepository.findByName("USER");
            if (defaultRole == null) {
                defaultRole = roleRepository.save(new Role("USER"));
            }
            user.setRole(defaultRole);
            info = "Користувач " + user.getUsername() + " отримав роль USER";
        } else {
            info = "Користувач " + user.getUsername() + " вже має роль: " + user.getRole().getName();
        }

        webSocketSender.sendAspectInfo("userroleaspect", info); // 🔔 надсилаємо
        return joinPoint.proceed();
    }
}


