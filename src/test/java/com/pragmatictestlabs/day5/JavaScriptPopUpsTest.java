package com.pragmatictestlabs.day5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class JavaScriptPopUpsTest {

    private WebDriver driver;

    @BeforeClass
    public void beforeClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = new ChromeDriver();
        driver.get("http://demosite.pragmatictestlabs.com/Alerts.html");
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void testAlertMessage() {
        //Test
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert(); //Alert is an interface
        Assert.assertEquals(alert.getText(), "This is a simple Alert");
        alert.accept(); //Click ok button is accept in alert
        Assert.assertEquals(driver.findElement(By.id("confirm-demo")).getText(), "Alert was closed!");
    }

    @Test
    public void testConfirmationOK() {
        driver.findElement(By.id("confirmation")).click();
        Alert confirmation = driver.switchTo().alert();
        Assert.assertEquals(confirmation.getText(), "Press a button!");
        confirmation.accept();
        Assert.assertEquals(driver.findElement(By.id("confirm-demo")).getText(), "It was confirmed!");

    }

    @Test
    public void testConfirmationCancel() {
        driver.findElement(By.id("confirmation")).click();
        Alert confirmation = driver.switchTo().alert();
        Assert.assertEquals(confirmation.getText(), "Press a button!");
        confirmation.dismiss();
        Assert.assertEquals(driver.findElement(By.id("confirm-demo")).getText(), "It was rejected!");

    }

    @Test
    public void testPrompt() {
        String name = "Janesh";
        driver.findElement(By.id("prompt")).click();
        Alert prompt = driver.switchTo().alert();
        Assert.assertEquals(prompt.getText(), "Please enter your name");
        prompt.sendKeys(name);
        prompt.accept();
        Assert.assertEquals(driver.findElement(By.id("confirm-demo")).getText(), "Your name is " + name);
    }
}