package com.vote.onlinevotingsystem.web;

import com.vote.onlinevotingsystem.model.entity.User;
import com.vote.onlinevotingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String admins() {
        return "admins";
    }

    @GetMapping("/{id}")
    public String getAdmin(Model model,
                           @PathVariable Long id) {

        User admin = userService.getAdmin(id);
        model.addAttribute("admin", admin);

        return "admin-manage";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return "redirect:/"; //TODO Redirect to previous page
    }

    @GetMapping("/{id}/profile")
    public String userProfile(@PathVariable Long id) {
        //TODO
        return "";
    }

    @PostMapping("/{id}/profile/update")
    public String updateProfile(@PathVariable Long id) {
        //TODO
        return "";
    }

    @GetMapping("/{id}/profile/change-password/")
    public String changePassword(@PathVariable Long id) {

        return "change-password";
    }

    @PostMapping("/{id}/profile/change-password/")
    public String changePassword(@PathVariable Long id,
                                 String password) {

        //TODO
        boolean isPassChanged = userService.changePassword(id, password);

        if (isPassChanged) {
            return "redirect:/";
        } else {
            return "redirect:/";
        }
    }
}
