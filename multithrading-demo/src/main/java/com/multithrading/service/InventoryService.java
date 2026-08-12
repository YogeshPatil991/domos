package com.multithrading.service;

import org.springframework.stereotype.Service;

@Service
public class InventoryService {

    public String checkInventory() {

        String threadName = Thread.currentThread().getName();
        System.out.println("checking inventory threadName: " + threadName);

        sleep(2000);

        return "Inventory Checking Done";

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
