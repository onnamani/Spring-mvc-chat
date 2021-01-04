package com.springmvc.homepage.controller;

import com.springmvc.homepage.POJO.User;
import com.springmvc.homepage.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignUpController {

    private UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String getSignUp(@ModelAttribute("userObject") User userObject) {
        return "signup";
    }

    @RequestMapping(value = "signup", method = RequestMethod.POST)
    public String postSignUp(@ModelAttribute("userObject") User userObject, Model model) {
        Integer userId = 0;

        if (userService.isUsernameAvailable(userObject.getUsername())) userId = userService.createUser(userObject);
        else {
            model.addAttribute("signupError", "The username already exists. Try another one.");
            model.addAttribute("signupSuccess", false);
            return "signup";
        }


        if (userId == null) {
            model.addAttribute("signupError", "Ooops Something went wrong. Please try again later");
            model.addAttribute("signupSuccess", false);
            return "signup";
        }
        else {
            model.addAttribute("signupSuccess", "true");
            return "redirect:/login";
        }
    }
}
