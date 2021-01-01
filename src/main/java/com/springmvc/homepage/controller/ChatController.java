package com.springmvc.homepage.controller;

import com.springmvc.homepage.POJO.ChatForm;
import com.springmvc.homepage.POJO.ChatMessage;
import com.springmvc.homepage.service.MessageListService;
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
    public String postChat(@ModelAttribute("chatObject") ChatForm chatObject, Model model) {
        messageList.addChatMessage(new ChatMessage(chatObject));
        model.addAttribute("chats", messageList.getChatMessages());
        chatObject.resetChatForm();
        return "chat";
    }

    @ModelAttribute("allOptions")
    public String[] allOptions() {
        return new String[] {"Say", "Shout", "Whisper"};
    }

}
