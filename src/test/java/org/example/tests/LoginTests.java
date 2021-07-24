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

    @BeforeMethod
    public void initTests() {

        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);

        homePage.waitUntilPageIsLoaded(); // wait for "Log in" is clickable ON THE HOMEPAGE
        loginPage
                .openPage() // wait for "Log in" ON THE HOMEPAGE is clickable + find and click on "Log in"
                .waitUntilPageIsLoaded(); // wait for "Log in" is clickable ON THE LOG IN PAGE BEFORE EMAIL & PASSWORD FIELDS FILLING

    }

     @Test
    public void negativeLogin() {

        loginPage.LoginNotAttl("romuska", "gromuska"); //this method replaces 3 methods above
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username", "The error message isn't correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegative_Sel_19")
    public void negativeLogin_Sel_19(String login, String password, String expectedCondidtion) {
        loginPage.LoginNotAttl(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(),
                expectedCondidtion, "The error message isn't correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginNegative_Sel_20")
    public void negativeLogin_Sel_20(String login, String password) {
        loginPage.LoginNotAttl(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(), "There isn't an account for this username"
                , "The error message isn't correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderThird")
    public void negativeLoginThirdDataProvider(String login, String password) {
        loginPage.LoginNotAttl(login, password);
        Assert.assertEquals(loginPage.getErrorMessage(),
                "There isn't an account for this username", "The error message isn't correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "loginPositive")
    public void positiveLogin(String login, String password) {
//        loginPage.loginAttl(LOGIN, PASSWORD); //this method replaces 4 methods above
        loginPage.loginAttl(login, password);
        boardsPage.waitUntilPageIsLoaded(); // wait for "Boards" button is clickable
        Assert.assertEquals(boardsPage.getBoardsButtonName(),"Boards", "Name of the button isn't 'Boards'");
    }
}
