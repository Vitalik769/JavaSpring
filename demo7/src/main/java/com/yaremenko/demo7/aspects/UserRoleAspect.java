package com.yaremenko.demo7.aspects;

import com.yaremenko.demo7.models.Role;
import com.yaremenko.demo7.models.User;
import com.yaremenko.demo7.repositories.RoleRepository;
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
    private static final Logger logger = Logger.getLogger(UserRoleAspect.class.getName());

    public UserRoleAspect(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Around("execution(* com.yaremenko.demo7.repositories.UserRepository.save(..)) && args(user,..)")
    @Transactional
    public Object setDefaultRoleIfNotAssigned(ProceedingJoinPoint joinPoint, User user) throws Throwable {
        if (user.getRole() == null) {
            Role defaultRole = roleRepository.findByName("USER");
            if (defaultRole == null) {
                defaultRole = roleRepository.save(new Role("USER"));
            }
            user.setRole(defaultRole);
            logger.info("✅ Аспект спрацював: Користувач " + user.getUsername() + " отримав роль USER");
        } else {
            logger.info("ℹ️ Користувач " + user.getUsername() + " вже має роль: " + user.getRole().getName());
        }

        return joinPoint.proceed(); // ❗ Передаємо змінений об'єкт далі в `save()`
    }
}
