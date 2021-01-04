//package com.springmvc.homepage.controller;
//
//import com.springmvc.homepage.POJO.MessageForm;
//import com.springmvc.homepage.service.MessageListService;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//
//@Controller
//public class HomeController {
//    private MessageListService messageListService;
//
//    public HomeController(MessageListService messageListService) {
//        this.messageListService = messageListService;
//    }
//
//    @GetMapping("/home")
//    public String getHome(@ModelAttribute("newMessage") MessageForm messageForm, Model model) {
//
//        model.addAttribute("greetings", this.messageListService.getMessages());
//        return "home";
//    }
//
//    @PostMapping("/home")
//    public String addMessage(@ModelAttribute("newMessage") MessageForm messageForm, Model model) {
//        messageListService.addMessage(messageForm.getText());
//        model.addAttribute("greetings", messageListService.getMessages());
//        messageForm.setText("");
//        return "home";
//    }
//}
