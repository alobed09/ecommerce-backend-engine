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
        
        System.out.println("بدء عملية الجرد لعدد منتجات: " + totalProducts);

        for (int i = 0; i < totalProducts; i += batchSize) {
            
            System.out.println("يتم الآن معالجة الدفعة (Chunk) من السجل: " + i);
            
            simulateHeavyProcessing();
        }
        
        System.out.println("تم الانتهاء من معالجة جميع الدفعات بنجاح.");
    }

    private void simulateHeavyProcessing() {
        try { Thread.sleep(100); } catch (InterruptedException e) {}
    }
}