package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class Home {
	
    @RequestMapping("/")
    public String home(Model modal) {
        modal.addAttribute("title", "SPRING BOOT - HELLO WORLD!");
        modal.addAttribute("message", "Welcome to SpringBoot");
        return "index";
    }
  
    
}
