package com.project1.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public String placeOrder(@RequestParam Long productId, @RequestParam Integer quantity) {
        orderService.placeOrder(productId, quantity);
        return "تم إتمام الطلب بنجاح وتم خصم المخزون!";
    }
}