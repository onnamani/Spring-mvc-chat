package com.springmvc.homepage.POJO;



public class ChatMessage {

    private String username;
    private String messageText;
    private ChatForm chat;
    private String[] bannedWords = {"silly", "idiot", "stupid"};
    private int bannedWordCount = 0;

    public ChatMessage(ChatForm chat) {
        this.chat = chat;
        this.username = chat.getUsername();
        bannedWordCount = this.bannedWordCheck(chat);

        if (this.bannedWordCount == 0) {

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
        } else {
            String temp = this.username;
            this.username = "system check";
            this.messageText = temp + ", Rephrase your message as it contains inappropriate words";
            this.bannedWordCount = 0;
        }
    }

    public String getUsername() {
        return this.username;
    }

    public String getMessageText() {
        return this.messageText;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setMessageText(String message) {
        this.messageText = message;
    }

    public int bannedWordCheck(ChatForm chat) {
        int count = 0;
        for (String bannedWord: this.bannedWords) {
            if (chat.getMessageText().toLowerCase().contains(bannedWord)) {
                count++;
            }
        }
        return count;
    }
}
