package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod(alwaysRun = true)
    public void initTests() {

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);

        log4j.startMethod("LoginTests - initTests()");

        homePage.waitUntilPageIsLoaded(); // wait for "Log in" is clickable ON THE HOMEPAGE
        loginPage
                .openPage() // wait for "Log in" ON THE HOMEPAGE is clickable + find and click on "Log in"
                .waitUntilPageIsLoaded(); // wait for "Log in" is clickable ON THE LOG IN PAGE BEFORE EMAIL & PASSWORD FIELDS FILLING

        log4j.endMethod("LoginTests - initTests()");

    }

    @Test
    public void negativeLogin() {

        loginPage.LoginNotAttl("romuska", "gromuska"); //this method replaces 3 methods above
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username", "The error message isn't correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegative_Sel_19")
    public void negativeLogin_Sel_19(String login, String password, String expectedCondidtion) {
        log4j.startTestCase("negativeLogin_Sel_19() => loginNegative different messages, login="
        + login + "password=" + password + "expectedCondidtion=" + expectedCondidtion + "'");
        log4j.info("Enter login not Attl: login=" + login + " password=" + password);
        loginPage.LoginNotAttl(login, password);
        log4j.info("Assert: Expected condidtion has to be - " + expectedCondidtion);
        Assert.assertEquals(loginPage.getErrorMessage(),
                expectedCondidtion, "The error message isn't correct");
        log4j.endTestCase2();
    }

    @Test(groups = {"system"}, dataProviderClass = DataProviders.class, dataProvider = "loginNegative_Sel_20")
    public void negativeLogin_Sel_20(String login, String password) {
        loginPage.LoginNotAttl(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this username"
                , "The error message isn't correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void negativeLoginThirdDataProvider(String login, String password) {
        loginPage.LoginNotAttl(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this email", "The error message isn't correct");
    }

    @Test(groups = {"smoke", "system"}, dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void positiveLogin(String login, String password) {
//        loginPage.loginAttl(LOGIN, PASSWORD); //this method replaces 4 methods above
        loginPage.loginAttl(login, password);
        boardsPage.waitUntilPageIsLoaded(); // wait for "Boards" button is clickable
        Assert.assertEquals(boardsPage.getBoardsButtonName(),"Boards", "Name of the button isn't 'Boards'");
    }
}
