package com.springmvc.homepage.POJOTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class ChatPage {

    @FindBy(id = "messageText")
    private WebElement messageText;

    @FindBy(id = "messageType")
    private WebElement messageType;

    @FindBy(id = "chat-submit")
    private WebElement chatSubmit;

    @FindBy(id = "chat-logout")
    private WebElement logout;

    @FindBy(id = "chat-username")
    private WebElement chatUsername;

    @FindBy(id = "chat-message")
    private WebElement chatMessage;

    public ChatPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void PostChat(String messageText, String messageType) {
        this.messageText.sendKeys(messageText);
        Select dropdown = new Select(this.messageType);
        dropdown.selectByValue(messageType);
        this.chatSubmit.click();
    }

    public String getChatUsername() {
        return this.chatUsername.getText();
    }

    public String getChatMessage() {
        return this.chatMessage.getText();
    }
}
