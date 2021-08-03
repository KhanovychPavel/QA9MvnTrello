package org.example.tests;

import org.example.pages.*;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HelpPageTest extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;
    MenuPageHelper menuPage;
    HelpPageHelper helpPage;

    @BeforeMethod
    public void initTests () {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9HaifaPage =  new CurrentBoardPageHelper(driver, "QA Haifa9");
        menuPage = PageFactory.initElements(driver, MenuPageHelper.class);
        helpPage = PageFactory.initElements(driver, HelpPageHelper.class);

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
                .waitUntilPageIsLoaded()
                .openHelpPage();
        helpPage.waitUntilPageIsLoaded();

    }

    @Test
    public void helpPageVerification() {
        Assert.assertEquals(helpPage.getHelpPageTagName(),"Get help with Trello",
                "Wrong page was opened");
    }

    @Test
    public void closeHelpWindowByXVerif() {
        helpPage.closeHelpPageAndSwitchToPreviousPage();
        qa9HaifaPage.waitUntilPageIsLoaded();
        Assert.assertTrue(qa9HaifaPage.isCorrectPage());
    }

    @Test
    public void returnToMyBoardsByPressGoToYourBoardButton() {
        helpPage.closeWindowByPressGoToYourBoardButton();
        boardsPage.waitUntilPageIsLoaded();
        Assert.assertTrue(boardsPage.isCorrectPage(), "Switched on wrong page!");

    }
}
