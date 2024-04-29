package com.pragmatictestlabs.pageObjectModel.tests;
import com.pragmatictestlabs.pageObjectModel.BaseTest;
import com.pragmatictestlabs.pageObjectModel.pages.LandingPage;
import com.pragmatictestlabs.pageObjectModel.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SourceLoginTest extends BaseTest {


    @Test
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.typeUsername("standard_user")
                .typePassword("secret_sauce")
                .clickLogin()
        ;

        //Work with the landing page
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertEquals(landingPage.getPageTitle(), "Products");

    }

    @Test
    public void testLoginWithValidCredentials2() {
        LoginPage loginPage = new LoginPage(driver);

        loginPage.login("standard_user", "secret_sauce");

        //Work with the landing page
        LandingPage landingPage = new LandingPage(driver);
        Assert.assertEquals(landingPage.getPageTitle(), "Products");

    }


    @Test
    public void testLoginWithInvalidPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "test1234");
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username and password do not match any user in this service");
    }

    @Test
    public void testLoginWithBlankUsernameAndPassword() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("", "");
        Assert.assertEquals(loginPage.getError(), "Epic sadface: Username is required");
    }

}