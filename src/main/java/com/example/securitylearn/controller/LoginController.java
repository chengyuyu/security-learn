package com.example.securitylearn.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/myLogin")
public class LoginController {

    @GetMapping
    public String demo(){
        return "myLogin";
    }
}
