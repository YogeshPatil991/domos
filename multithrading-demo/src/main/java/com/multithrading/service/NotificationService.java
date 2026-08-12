package com.multithrading.service;

import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    public String sendNotification() {

        String threadName = Thread.currentThread().getName();
        System.out.println("send notification threadName: " + threadName);

        sleep(2000);

        return "Send Notification Done";

    }

    private void sleep(long millis) {
        try {

            Thread.sleep(millis);

        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }
    }

}
