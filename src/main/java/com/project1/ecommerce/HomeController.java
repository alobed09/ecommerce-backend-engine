package com.project1.ecommerce;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

   
    @Value("${server.port:8080}")
    private String serverPort;

    @GetMapping("/")
    public String home() {
        return "<div style='text-align: center; margin-top: 50px; font-family: Arial, sans-serif;'>" +
               "<h1 style='color: #2c3e50;'>مرحباً بك في نظام المتجر الإلكتروني الذكي </h1>" +
               "<h2 style='color: #27ae60;'>تم استلام طلبك ومعالجته بنجاح</h2>" +
               "<h3>تمت الخدمة بواسطة الخادم (Node) الذي يعمل على المنفذ رقم: <span style='color: #e74c3c; font-size: 2em;'>" + serverPort + "</span></h3>" +
               "<p style='font-size: 1.2em; color: #7f8c8d;'>نظام توزيع الأحمال (Load Balancer) يعمل بكفاءة </p>" +
               "</div>";
    }
}