package com.pragmatictestlabs.pageObjectModel.pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
    private final WebDriver driver;

    //Elements
    @FindBy(css = "span.title")
    WebElement pageTitle;

    public LandingPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }


    public String getPageTitle() {
        return pageTitle.getText().trim();
    }
}