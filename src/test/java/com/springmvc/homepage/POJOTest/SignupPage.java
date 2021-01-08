package com.springmvc.homepage.POJOTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "username")
    private WebElement username;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(id = "firstname")
    private WebElement firstname;

    @FindBy(id = "lastname")
    private WebElement lastname;

    @FindBy(id = "signup-submit")
    private WebElement submit;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void submitSignup(
            String username,
            String password,
            String firstname,
            String lastname
    ) {
        this.username.clear();
        this.password.clear();
        this.firstname.clear();
        this.lastname.clear();

        this.username.sendKeys(username);
        this.password.sendKeys(password);
        this.firstname.sendKeys(firstname);
        this.lastname.sendKeys(lastname);

        this.submit.click();
    }

}
