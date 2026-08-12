package com.multithrading.service;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    public String paymentProcess() {

        String threadName = Thread.currentThread().getName();
        System.out.println("payment process threadName: " + threadName);

        sleep(2000);

        return "Payment Successful";

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
