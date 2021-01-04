package com.springmvc.homepage.controller;

import com.springmvc.homepage.POJO.User;
import com.springmvc.homepage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignUp(@ModelAttribute("userObject") User userObject) {
        return "signup";
    }

    @PostMapping
    public String postSignUp(@ModelAttribute("userObject") User userObject, Model model) {
        Integer userId = 0;

        if (userService.isUsernameAvailable(userObject.getUsername())) userId = userService.createUser(userObject);
        else model.addAttribute("signupError", "The username already exists. Try another one.");


        if (userId == null) model.addAttribute("signupError","Ooops Something went wrong. Please try again later");
        else model.addAttribute("signupSuccess", true);



        return "/signup";
    }
}
