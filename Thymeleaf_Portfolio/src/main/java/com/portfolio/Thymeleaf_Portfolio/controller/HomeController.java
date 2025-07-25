package com.portfolio.Thymeleaf_Portfolio.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping
    public String showIndex(){
        return "index";
    }

    @GetMapping("/resume")
    public String showResume(){
        return "resume";
    }
}
