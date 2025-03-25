package com.yaremenko.demo7.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
    private final JavaMailSender mailSender;

    public MailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendAspectInfo(String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("Lenaniseve33@gmail.com");
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }
}

