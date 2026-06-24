package com.project1.ecommerce.config;

import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync 
public class AsyncConfig {
    

    /**
     * التحكم في سعة الموارد الحاسوبية:[Resource Management - المتطلب 2]
     * لحماية النظام من الانهيار تحت الضغط العالي(Crash)  تم تحديد حجم مجمع الخيوط
     * (Core: 5, Max: 10)
     * بدلاً من استهلاك الذاكرة بشكل عشوائي (Queue Capacity: 100)  الطلبات الزائدة يتم وضعها في طابور انتظار منظم 
     */  
    @Bean(name = "orderExecutor")
    public Executor orderExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);  
        executor.setMaxPoolSize(10);  
        executor.setQueueCapacity(100);
        executor.setThreadNamePrefix("OrderThread-");
        executor.initialize();
        return executor;
    }
}