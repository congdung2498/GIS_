package com.example.demo.controller;

import com.example.demo.NativeSql;
import com.example.demo.model.District;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.List;
import org.hibernate.annotations.Parameter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class Home {
	
    @RequestMapping("/")
    public String home(Model modal) {
        modal.addAttribute("title", "SPRING BOOT - HELLO WORLD!");
        modal.addAttribute("message", "Welcome to SpringBoot");
        return "index";
    }
  
    
}
