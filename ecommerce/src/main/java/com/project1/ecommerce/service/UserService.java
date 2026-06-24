package com.project1.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project1.ecommerce.model.User;
import com.project1.ecommerce.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
       
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("عذراً، هذا البريد الإلكتروني مسجل مسبقاً!");
        }
        
        
        
        return userRepository.save(user);
    }
}