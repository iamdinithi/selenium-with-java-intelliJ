package com.pragmatictestlabs.day2.crossBrowserTesting;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FireFoxTest {
    private WebDriver driver;

    //Execute only once per test suite
    @BeforeSuite
    public void beforeSuite() {

        //Setup  web browser driver - (Safari)
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeMethod
    public void beforeMethod (){

        //Open a web browser (Chrome)
        driver = new FirefoxDriver();

        //Navigate to login (Type URL)
        driver.get("https://www.saucedemo.com/");

    }

    @AfterMethod
    public void  closeBrowser (){

        //Close the Browser
        //difference of quit and close : both close browser window. close only close current window. quit close all the  browser windows
        if(driver!=null){
            driver.quit();
        }
    }

    @Test
    public void helloSelenium() {

        //Type username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //Type Password
        driver.findElement(By.id("password")).sendKeys(("secret_sauce"));

        //Click the login
        driver.findElement(By.id("login-button")).click();

        //Verify product label available on landing page
        Assert.assertEquals(driver.findElement(By.cssSelector("span.title")).getText(),"Products");

    }

}
