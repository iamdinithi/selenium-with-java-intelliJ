package com.pragmatictestlabs.day4;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class IntroductionToSyncTest {

    private WebDriver driver;

    @BeforeMethod
    public  void beforeMethod(){
        WebDriverManager.chromedriver().setup();

        driver= new ChromeDriver();
        driver.navigate().to("https://eviltester.github.io/synchole/collapseable.html");
        //driver.navigate().to("https://eviltester.github.io/synchole/buttons.html");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }

    @Test
    public  void testCollapseableDiv() throws InterruptedException {

        //Click section
        driver.findElement(By.cssSelector("section.condense")).click();

        //Thread.sleep(5000); NEVER USE

        //2 ways to use --- 1. strike
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS); This is deprecated abd avoid using
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        // implicitlyWait -- wait for element intractable, don't use implicit and explicit together

        //Click above link
        driver.findElement(By.cssSelector("a#aboutLink")).click();

        //Verify
        Assert.assertTrue(driver.getCurrentUrl().contains("about.html"));
    }

    @Test
    public  void testWithExplicitWait() throws InterruptedException {


        //Click section
        driver.findElement(By.cssSelector("section.condense")).click();


        //Explicit wait ---> wait till the link is visible
        new WebDriverWait(driver,Duration.ofSeconds(5),Duration.ofMillis(50))
                .pollingEvery(Duration.ofMillis(50))
                .ignoring(NullPointerException.class)
                .withMessage("About link is not clickable")
                .until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#aboutLink")));
        //By default 5 seconds if time is not defined

        /**
         * 0: cHECK CONDITION : if satisfied continue else wait/sleep 500ms
         * 500: cHECK CONDITION : if satisfied continue else wait/sleep 500ms
         * 4500: cHECK CONDITION : if satisfied continue else wait/sleep 500ms
         * 5000: cHECK CONDITION : if satisfied continue else wait/sleep timeout error
         *  **/


        //Click above link
        driver.findElement(By.cssSelector("a#aboutLink")).click();

        //Verify
        Assert.assertTrue(driver.getCurrentUrl().contains("about.html"));
    }

    @Test
    public  void testButtons() throws InterruptedException {

        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        //Click button1
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#easy00"))).click();
        //driver.findElement(By.id("#easy00")).click();

        //Click button2
        //driver.findElement(By.cssSelector("#easy01")).click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#easy01"))).click();

        //Click button3
        //driver.findElement(By.cssSelector("#easy02")).click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#easy02"))).click();

        //Click button4
        //driver.findElement(By.cssSelector("#easy03")).click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.id("easy03"))).click();

        //Verify
        Assert.assertEquals(driver.findElement(By.id("easybuttonmessage")).getText(),"All Buttons Clicked");
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds(0)));
    }

    @Test
    public  void testHardToSyncButtons() throws InterruptedException {

        //Click button1
        //driver.findElement(By.cssSelector("#button00")).click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#button00"))).click();

        //Click button2
        //driver.findElement(By.cssSelector("#button01")).click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#button01"))).click();

        //Click button3
        //driver.findElement(By.cssSelector("#button02")).click();
        new WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#button02"))).click();

        //Click button4
        //driver.findElement(By.cssSelector("#button03")).click();
        new WebDriverWait(driver,Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("#button03"))).click();


        //Verify
        Assert.assertEquals(driver.findElement(By.id("buttonmessage")).getText(),"All Buttons Clicked");
        driver.manage().timeouts().implicitlyWait((Duration.ofSeconds(0)));
    }

}

