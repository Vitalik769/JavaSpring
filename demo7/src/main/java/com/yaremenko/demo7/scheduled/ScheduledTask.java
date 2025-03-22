package com.yaremenko.demo7.scheduled;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(initialDelay = 1300, fixedRate = 6800)
    public void printTopicToConsole() {
        System.out.println("🎯 Тема варіанту: API для системи керування доступом");
    }
}
