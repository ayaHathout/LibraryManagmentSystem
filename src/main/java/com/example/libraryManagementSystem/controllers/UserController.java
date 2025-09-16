package com.example.libraryManagementSystem.controllers;

import com.example.libraryManagementSystem.entities.User;
import com.example.libraryManagementSystem.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public String getUsers() {
        return "Hello World2222222222";
    }

    @GetMapping("/login")
    public User login(@AuthenticationPrincipal(expression = "username") String username) {
        System.out.println("username = " + username);
        return userService.getByUserName(username).orElseThrow(() -> new RuntimeException("User Not Found"));
    }
}
