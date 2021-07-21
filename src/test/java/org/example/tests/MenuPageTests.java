package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.CurrentBoardPageHelper;
import org.example.pages.LoginPageHelper;
import org.example.pages.MenuPageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuPageTests extends TestBase {
//    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;
    MenuPageHelper menuPage;

    @BeforeMethod
    public void initTests() {
//        homePage = new HomePageHelper(driver);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9HaifaPage = new CurrentBoardPageHelper(driver, "QA Haifa9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage
                .openPage()
                .waitUntilPageIsLoaded()
                .loginAttl(LOGIN, PASSWORD);
        boardsPage
                .waitUntilPageIsLoaded()
                .boardsButtonInWorkspacesMenuClick();
        qa9HaifaPage
                .openPage()
                .waitUntilPageIsLoaded();
        menuPage
                .openPage()
                .waitUntilPageIsLoaded();
    }

    @Test
    public void profileVisibilityMenuExists() {
        Assert.assertEquals(menuPage.getProfileVisibilityMenuName(), "Profile and visibility");
    }


}
