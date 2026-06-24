package com.project1.ecommerce.batch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.project1.ecommerce.repository.ProductRepository;

@Component
public class DailySalesBatch {

    @Autowired
    private ProductRepository productRepository;

    
    @Scheduled(cron = "0 0 0 * * *") 
    public void processInventoryAudit() {
        int batchSize = 50; 
        long totalProducts = productRepository.count();
        
        System.out.println("--> [Batch Process]: Starting inventory audit for total products: " + totalProducts);

        for (int i = 0; i < totalProducts; i += batchSize) {
            
            System.out.println("--> [Batch Process]: Processing chunk starting at record: " + i);
            
            simulateHeavyProcessing();
        }
        
        System.out.println("--> [Batch Process]: Successfully completed processing all chunks.");
    }

    private void simulateHeavyProcessing() {
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
}