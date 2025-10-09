package com.health.HealthFirst.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping()
public class HomeController {
    //@RequestMapping("/")
    public String greet(){
        return "Hello!!";
    }
}
