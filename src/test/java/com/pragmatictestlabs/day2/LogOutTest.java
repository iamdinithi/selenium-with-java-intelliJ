package com.pragmatictestlabs.day2;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LogOutTest {

    private  WebDriver driver;

    //Execute only once per test suite
    @BeforeSuite
    public void beforeSuite() {

        //Setup  web browser driver (Chrome)
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void beforeMethod (){

        //Open a web browser (Chrome)
        driver = new ChromeDriver();

        //Navigate to login (Type URL)
        driver.navigate().to("https://www.saucedemo.com/");

        Assert.assertEquals(driver.getTitle(),"Swag Labs");
        /*
        //refresh browser
        driver.navigate().refresh();

        //back browsing
        driver.navigate().back();

        //forward browsing
        driver.navigate().forward(); */

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
    public void loginUser() {

        //Clear fields
        //Type username
        driver.findElement(By.id("user-name")).clear();

        //Type username
        driver.findElement(By.id("user-name")).sendKeys("standard_user");

        //Type Password
        driver.findElement(By.id("password")).sendKeys(("secret_sauce"));

        //Click the login
        driver.findElement(By.id("login-button")).click();

        //Verify product label available on landing page
        Assert.assertEquals(driver.findElement(By.cssSelector("span.title")).getText(),"Products");

        //click on hamburger Menu
        driver.findElement(By.cssSelector("button[id='react-burger-menu-btn']")).click();

        //click on log out
        driver.findElement(By.cssSelector("a[id='logout_sidebar_link']")).click();

        //Assert navigation to login page
        Assert.assertEquals(driver.findElement(By.className("login_logo")).getText(),"Swag Labs");

    }

}
