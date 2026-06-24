package com.project1.ecommerce.service; // أو com.project1.ecommerce.controller

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {   

    @Autowired
    private CartService cartService;

       @PostMapping("/add")
    public String addToCart(@RequestParam Long userId, @RequestParam Long productId, @RequestParam Integer quantity) {
        return cartService.addToCart(userId, productId, quantity);
    }

    

    @GetMapping("/{userId}")
public java.util.List<com.project1.ecommerce.model.CartItem> viewCart(@PathVariable Long userId) {
    return cartService.getCartByUserId(userId); 
}
}