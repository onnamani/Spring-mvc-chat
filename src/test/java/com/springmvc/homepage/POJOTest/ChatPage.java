package com.springmvc.homepage.POJOTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "chat-submit")
    private WebElement chatSubmit;

    @FindBy(id = "chat-logout")
    private WebElement logout;

    @FindBy(className = "chat-username")
    private List<WebElement> chatUsername;

    @FindBy(className = "chat-message")
    private List<WebElement> chatMessage;

    @FindBy(className = "chats")
    private List<WebElement> chats;


    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void PostChat(String messageText, String messageType) {
        this.messageText.sendKeys(messageText);
        Select dropdown = new Select(this.messageType);
        dropdown.selectByValue(messageType);
        this.chatSubmit.click();
    }

    public List<WebElement> getChatUsername() {
        return this.chatUsername;
    }

    public List<WebElement> getChatMessage() {
        return this.chatMessage;
    }

    public WebElement getLogout() {
        return this.logout;
    }

    public List<WebElement> getChats() {
        return this.chats;
    }
}
