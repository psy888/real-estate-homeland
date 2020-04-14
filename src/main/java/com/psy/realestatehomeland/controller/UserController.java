package com.psy.realestatehomeland.controller;

import com.psy.realestatehomeland.model.user.UserEntity;
import com.psy.realestatehomeland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("msg", "Already logged in!");
            return "403Page";
        }
        model.addAttribute("title", "Login");

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model, Principal principal) {
        if (principal != null) {
            model.addAttribute("msg", "Already logged in!");
            return "403Page";
        }
        model.addAttribute("user", new UserEntity());
        model.addAttribute("title", "Register");

        return "register";
    }

    @PostMapping("/register")
    public String register(UserEntity user) {

        userService.createUser(user);

        return "redirect:/login";
    }


}
