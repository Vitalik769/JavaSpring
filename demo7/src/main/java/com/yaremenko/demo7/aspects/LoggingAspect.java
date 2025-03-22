package com.yaremenko.demo7.aspects;

import com.yaremenko.demo7.services.MailService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.logging.Logger;

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = Logger.getLogger(LoggingAspect.class.getName());
    private final MailService mailService;

    public LoggingAspect(MailService mailService) {
        this.mailService = mailService;
    }

    @AfterReturning(pointcut = "execution(* com.yaremenko.demo7.controllers.AccessController.*(..))", returning = "result")
    public void logAccessControllerMethods(JoinPoint joinPoint, Object result) {
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        String message = "Метод: " + methodName + " | Аргументи: " + Arrays.toString(args) + " | Результат: " + result;
        logger.info("📌 " + message);
        mailService.sendAspectInfo("AccessController Method Called", message);
    }
}