package com.vote.onlinevotingsystem.web;

import com.vote.onlinevotingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginFail(Model model) {
        model.addAttribute("bad-credentials", true);

        return "login";
    }
}
