package com.project1.ecommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project1.ecommerce.model.CartItem;
import com.project1.ecommerce.model.Product;
import com.project1.ecommerce.model.User;
import com.project1.ecommerce.repository.CartItemRepository;
import com.project1.ecommerce.repository.ProductRepository;
import com.project1.ecommerce.repository.UserRepository;

@Service
public class CartService {


    public java.util.List<com.project1.ecommerce.model.CartItem> getCartByUserId(Long userId) {
    return cartItemRepository.findByUserId(userId);
    }

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public String addToCart(Long userId, Long productId, Integer quantity) {
        
        
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("المستخدم غير موجود"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("المنتج غير موجود"));

       
        if (product.getStockQuantity() < quantity) {
            throw new RuntimeException("عذراً، المخزون الحالي لا يكفي لطلبك!");
        }

        
        Optional<CartItem> existingCartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (existingCartItem.isPresent()) {
            
            CartItem cartItem = existingCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
            cartItemRepository.save(cartItem);
        } else {
            
            CartItem newItem = new CartItem();
            newItem.setUser(user);
            newItem.setProduct(product);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }

        return "تمت إضافة المنتج إلى السلة بنجاح!";
    }
}