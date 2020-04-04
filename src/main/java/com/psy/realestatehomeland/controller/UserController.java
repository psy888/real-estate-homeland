package com.psy.realestatehomeland.controller;

import com.psy.realestatehomeland.model.user.UserEntity;
import com.psy.realestatehomeland.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(){

        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){

        model.addAttribute("user", new UserEntity());

        return "register";
    }

    @PostMapping("/register")
    public String register(UserEntity user){

        userService.createUser(user);

        return "redirect:/";
    }

}
