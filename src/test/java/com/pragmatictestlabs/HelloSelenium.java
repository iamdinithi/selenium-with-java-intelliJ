package com.pragmatictestlabs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HelloSelenium {

    @Test
    public void helloSelenium () {

        //Setup  web browser driver (Chrome)
        WebDriverManager.chromedriver().setup();

        //Open a web browser (Chrome)
        WebDriver driver = new ChromeDriver();

        //Navigate to login (Type URL)
        driver.get("https://www.saucedemo.com/");

        //Type username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //Type Password
        driver.findElement(By.id("password")).sendKeys(("secret_sauce"));

        //Click the login
        driver.findElement(By.id("login-button")).click();

        //Verify product label available on landing page
        Assert.assertEquals(driver.findElement(By.cssSelector("span.title")).getText(),"Products");

        //Close the Browser
        //difference of quit and close : both close browser window. close only close current window. quit close all the  browser windows
        driver.quit();


    }

}
