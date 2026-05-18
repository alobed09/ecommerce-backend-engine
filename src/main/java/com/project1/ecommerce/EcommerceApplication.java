package com.project1.ecommerce;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.project1.ecommerce.model.Product;
import com.project1.ecommerce.repository.ProductRepository;

@SpringBootApplication
@EnableScheduling
public class EcommerceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }

    @Bean
    public CommandLineRunner initDatabase(ProductRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                Product p = new Product();
                p.setName("Laptop");
                p.setPrice(1200.0);
                p.setStockQuantity(10);
                
                repository.save(p);
                System.out.println("✅ تم إضافة المنتج التجريبي بنجاح!");
            }
        };
    }
}