package com.vote.onlinevotingsystem.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class HomeController {

    public HomeController() {
    }

    @GetMapping
    public String home() {
        return "index";
    }
}
