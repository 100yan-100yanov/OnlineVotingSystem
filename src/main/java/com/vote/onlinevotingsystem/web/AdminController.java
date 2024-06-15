package com.vote.onlinevotingsystem.web;

import com.vote.onlinevotingsystem.model.dto.ChangePasswordDTO;
import com.vote.onlinevotingsystem.model.dto.ProfileUpdateDTO;
import com.vote.onlinevotingsystem.model.entity.User;
import com.vote.onlinevotingsystem.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

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

        User admin = userService.getUserById(id);
        model.addAttribute("admin", admin);

        return "admin-manage";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return "redirect:/admins";
    }

    @GetMapping("/{id}/profile")
    public String userProfile(Model model,
                              @PathVariable Long id) {

        User user = userService.getUserById(id);
        model.addAttribute("user", user);

        return "profile";
    }

    @PutMapping("/{id}/profile/update")
    public String updateProfile(@PathVariable Long id,
                                ProfileUpdateDTO profileUpdateDTO) {

        userService.updateProfile(id, profileUpdateDTO);

        return "redirect:/admins/{id}/profile";
        //TODO Make it RESTful
    }

    @GetMapping("/profile/change-password/")
    public String changePassword(@ModelAttribute("changePasswordDTO")
                                 ChangePasswordDTO changePasswordDTO) {
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
