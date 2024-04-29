package com.pragmatictestlabs.day5;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SelectTestExample {

    WebDriver driver;

    @BeforeMethod
    public void testBeforeTest(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("http://demosite.pragmatictestlabs.com");
    }

    @AfterMethod
    public void afterMethod(){
        if(driver!=null){
            driver.quit();
        }
    }


    @Test
    public void testSelect(){
        driver.findElement(By.id("btnRegister")).click();
        Select selContinent = new Select(driver.findElement(By.id("continents")));
        selContinent.selectByIndex(2); //3rd option is selected
        selContinent.selectByValue("South America"); //value in the code
        selContinent.selectByVisibleText(" North America "); //visible text may have spaces and those should be included to work
    }




}