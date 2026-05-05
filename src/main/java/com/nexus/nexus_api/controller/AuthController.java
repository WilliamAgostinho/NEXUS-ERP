package com.nexus.nexus_api.controller;

import com.nexus.nexus_api.entity.Employee;
import com.nexus.nexus_api.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

//    @Autowired
//    private AuthService authService;

    @PostMapping("/login")
    public String login(@RequestBody Employee employee){
        return "teste";
    }
}
