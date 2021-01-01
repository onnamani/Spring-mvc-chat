package com.springmvc.homepage.POJO;


public class ChatMessage {

    private String username;
    private String messageText;
    private ChatForm chat;

    public ChatMessage(ChatForm chat) {
        this.chat = chat;
        this.username = chat.getUsername();
        System.out.println(chat.getUsername());
        System.out.println(chat.getMessageText());
        System.out.println(chat.getMessageType());

        switch (chat.getMessageType()) {
            case "Shout":
                this.messageText = chat.getMessageText().toUpperCase();
                break;

            case "Whisper":
                this.messageText = chat.getMessageText().toLowerCase();
                break;

            default:
                this.messageText = chat.getMessageText();
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getMessageText() {
        return this.messageText;
    }
}
