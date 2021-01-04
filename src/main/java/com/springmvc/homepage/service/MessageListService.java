package com.springmvc.homepage.service;

import com.springmvc.homepage.POJO.ChatForm;
import com.springmvc.homepage.mapper.MessageMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class MessageListService {

    private MessageMapper messageMapper;
    private String[] bannedWords = {"silly", "idiot", "stupid"};

    public MessageListService(MessageMapper messageMapper) {
        this.messageMapper = messageMapper;
    }

    public Integer insertChat(ChatForm chatForm) {
        int bannedWordCount = bannedWordCheck(chatForm);

        if (bannedWordCount == 0) {
            switch (chatForm.getMessageType()) {
                case "Shout":
                    chatForm.setMessageText(chatForm.getMessageText().toUpperCase());
                    break;
                case "Whisper":
                    chatForm.setMessageText(chatForm.getMessageText().toLowerCase());
                    break;
            }
        } else {
            String temp = chatForm.getUsername();
            chatForm.setUsername("system check");
            chatForm.setMessageText(temp + ", Rephrase your message as it contains inappropriate words");
        }

        return this.messageMapper.insertMessage(chatForm);
    }

    public List<ChatForm> getChatMessages() {
        return this.messageMapper.getAllChatMessages();
    }

    public int bannedWordCheck(ChatForm chat) {
        int count = 0;
        for (String bannedWord : this.bannedWords) {
            if (chat.getMessageText().toLowerCase().contains(bannedWord)) {
                count++;
            }
        }
        return count;
    }
}
