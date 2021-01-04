package com.springmvc.homepage.controller;

import com.springmvc.homepage.POJO.ChatForm;
import com.springmvc.homepage.service.MessageListService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/chat")
public class ChatController {

    private MessageListService messageList;

    public ChatController(MessageListService messageList) {
        this.messageList = messageList;
    }

    @GetMapping
    public String getChat(@ModelAttribute("chatObject") ChatForm chatObject, Model model) {
        model.addAttribute("chats", messageList.getChatMessages());
        return "chat";
    }

    @PostMapping
    public String postChat(Authentication authentication, @ModelAttribute("chatObject") ChatForm chatObject,
                           Model model) {
        chatObject.setUsername(authentication.getName());
        messageList.insertChat(chatObject);
        chatObject.setMessageText("");
        model.addAttribute("chats", messageList.getChatMessages());

        return "chat";
    }

    @ModelAttribute("allOptions")
    public String[] allOptions() {
        return new String[] {"Say", "Shout", "Whisper"};
    }

}
