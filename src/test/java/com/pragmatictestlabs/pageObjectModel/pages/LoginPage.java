package com.pragmatictestlabs.pageObjectModel.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    //Elements
    @FindBy(id = "user-name")
    WebElement txtUsername;

    @FindBy(id = "password")
    WebElement txtPassword;


    @FindBy(id = "login-button")
    WebElement btnLogin;

    @FindBy(css = "div h3")
    WebElement msgError;



    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        //Initialize the elements
        PageFactory.initElements(this.driver, this);
    }


    public LoginPage typeUsername(String username) {
        txtUsername.sendKeys(username);
        return this;
    }

    public LoginPage typePassword(String password) {
        txtPassword.sendKeys(password);
        return this;
    }

    public void clickLogin() {
        btnLogin.click();
    }


    public void login(String username, String password) {
        this.typeUsername(username).typePassword(password).clickLogin();
    }

    public String   getError() {
        return msgError.getText().trim();
    }
}