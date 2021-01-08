package com.springmvc.homepage.POJOTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "login-submit")
    private WebElement submit;

    @FindBy(id = "back-to-signup")
    private WebElement backToSignup;

    @FindBy(id = "success")
    private WebElement successMessage;

    @FindBy(id = "logout")
    private WebElement logoutMessage;

    @FindBy(id = "error")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void login(String username, String password) {
        this.username.sendKeys(username);
        this.password.sendKeys(password);

        this.submit.click();
    }

    public String getFlashMessage() {

        if (successMessage != null) {
            return successMessage.getText();
        } else if (errorMessage != null) {
            return errorMessage.getText();
        } else if (logoutMessage != null) {
            return logoutMessage.getText();
        }

        return "";
    }
}
