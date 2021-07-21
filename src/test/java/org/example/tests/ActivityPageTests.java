package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ActivityPageTests extends TestBase {
//    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;
    MenuPageHelper menuPage;
    ActivityPageHelper activityPage;

    @BeforeMethod
    public void initTests() {
//        homePage = new HomePageHelper(driver);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9HaifaPage = new CurrentBoardPageHelper(driver, "QA Haifa9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        activityPage = PageFactory.initElements(driver, ActivityPageHelper.class);

        homePage.waitUntilPageIsLoaded();
        loginPage.openPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginAttl(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
        boardsPage.boardsButtonInWorkspacesMenuClick();
        qa9HaifaPage.openPage();
        qa9HaifaPage.waitUntilPageIsLoaded();
    }

    @Test
    public void confirmInfoNewListCreated() {
        String listTitle = "Sel-14";
        qa9HaifaPage
                .newListCreating(listTitle);
        menuPage
                .openPage()
                .waitUntilPageIsLoaded()
                .openActivityPage();
        activityPage
                .waitUntilPageIsLoaded();

        Assert.assertTrue(activityPage.textReceiveLastListAdded().contains(listTitle),
                "Information about the newly created list isn't displayed. No new list has been created.");

        Assert.assertTrue(activityPage.textReceiveLastListAddedFromActivity(listTitle));
    }

}
