package com.project1.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project1.ecommerce.model.Product;
import com.project1.ecommerce.repository.ProductRepository;

@Service
public class OrderService {

    @Autowired
    private ProductRepository productRepository;

    
    @Transactional 
    public void placeOrder(Long productId, Integer quantity) {
      
        Product product = productRepository.findById(productId)
            .orElseThrow(() -> new RuntimeException("المنتج غير موجود"));

        
        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("المخزون غير كافٍ!");
        }
        
       
        product.setStockQuantity(product.getStockQuantity() - quantity);
        
        
        productRepository.save(product);

       
        processAsyncTasks(productId);
    }

    @Async("orderExecutor") 
    public void processAsyncTasks(Long productId) {
        System.out.println("خيط المعالجة: " + Thread.currentThread().getName() + " يقوم بتوليد فاتورة للمنتج " + productId);
    }
}