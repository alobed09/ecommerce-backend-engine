package com.project1.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project1.ecommerce.model.Product;
import com.project1.ecommerce.repository.ProductRepository;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

   
  @PutMapping("/{id}")
  @Transactional
  public Product updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
    Product product = productRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Product not found"));

   
     if (productDetails.getVersion() == null || !product.getVersion().equals(productDetails.getVersion())) {
        throw new org.springframework.orm.ObjectOptimisticLockingFailureException(Product.class, id);
     }

     product.setName(productDetails.getName());
     product.setPrice(productDetails.getPrice());
    
    
     product.setVersion(product.getVersion() + 1); 
    
      return productRepository.save(product);
    }


// المتطلب 10: محاكاة اختناق في الأداء (تأخير ثانيتين) + المتطلب 6: التخزين المؤقت
    @GetMapping("/{id}")
    @Cacheable(value = "products", key = "#id")
    public Product getProduct(@PathVariable Long id) throws InterruptedException {
        
        
        Thread.sleep(2000); 
        
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    //لعرض كافة المنتجات في المتجر
    @GetMapping("/all")
    public Iterable<Product> getAllProducts() {
        return productRepository.findAll();
    }
}