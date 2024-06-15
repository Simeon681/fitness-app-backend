package com.example.fitnessapp1.service;

import org.springframework.scheduling.annotation.Async;

public interface EmailSenderService {
    @Async
    void sendEmail(String to, String text);

    String buildEmail(String name, String link);
}
