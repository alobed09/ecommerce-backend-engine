package com.project1.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer stockQuantity;
    

    /**
     * [Synchronization Point - المتطلب 1 و 7]:(Optimistic Locking) تطبيق القفل المتفائل 
     *عند محاولة عدة خيوط (Race Condition)هذا الحقل يمنع مشكلة تضارب البيانات
     *(Thread-Safe Concurrency) تعديل كمية نفس المنتج في نفس اللحظة، مما يضمن سلامة التزامن 
     */
   
    @Version
    private Long version;
}