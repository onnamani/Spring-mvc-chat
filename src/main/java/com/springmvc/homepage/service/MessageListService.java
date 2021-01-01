package com.springmvc.homepage.service;

import com.springmvc.homepage.POJO.ChatMessage;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class MessageListService {

    private List<String> messages;
    private List<ChatMessage> chatMessages;

    @PostConstruct
    public void postConstruct() {
        this.messages = new ArrayList<>();
        this.chatMessages = new ArrayList<>();
    }

    public void addChatMessage(ChatMessage chatMessage) {
        chatMessages.add(chatMessage);
    }

    public List<ChatMessage> getChatMessages() {
        return this.chatMessages;
    }

    public void addMessage(String message) {
        messages.add(message);
    }

    public List<String> getMessages() {
        return messages;
    }
}
