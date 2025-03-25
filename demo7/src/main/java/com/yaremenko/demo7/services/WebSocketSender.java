package com.yaremenko.demo7.services;

import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketSender {
    private final SimpMessagingTemplate messagingTemplate;

    public WebSocketSender(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    public void sendAspectInfo(String aspectName, String message) {
        String path = "/topic/" + aspectName.toLowerCase(); // Наприклад: /topic/userroleaspect
        messagingTemplate.convertAndSend(path, message);
    }
}

