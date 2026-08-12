package com.multithrading.service;

import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public String calculateShipping() {

        String threadName = Thread.currentThread().getName();
        System.out.println("Calculate shipping threadName: " + threadName);

        sleep(2000);

        return "Calculate Shipping Done";

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
