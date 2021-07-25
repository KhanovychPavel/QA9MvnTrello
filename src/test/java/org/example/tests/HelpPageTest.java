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
                .waitUntilPageIsLoaded();

    }

    @Test
    public void helpWindowTests() {
        String firstWindowHandle = helpPage.getWindowHandle();
        int windowsSizeBefore = helpPage.getHandlesSize();
        menuPage.openHelpPage();
        helpPage.waitUntilPageIsLoaded();
        int windowsSizeAfter = helpPage.getHandlesSize();

        if(windowsSizeAfter != windowsSizeBefore + 1) {
            helpPage.printOpeningErrorMessage();
            return;
        }
        helpPage.switchToSecondWindow(firstWindowHandle);

        Assert.assertEquals(helpPage.getHelpPageTagName(),"Get help with Trello",
                "Wrong page was opened");
    }

    @Test
    public void goToYourBoards() {
        String firstWindowHandle = helpPage.getWindowHandle();
        int windowsSizeBefore = helpPage.getHandlesSize();
        menuPage.openHelpPage();
        helpPage.waitUntilPageIsLoaded();
        int windowsSizeAfter = helpPage.getHandlesSize();

        if(windowsSizeAfter != windowsSizeBefore + 1) {
            helpPage.printOpeningErrorMessage();
            return;
        }
        String secondWindowHandle = helpPage.getSecondWindowHandle(firstWindowHandle);
        helpPage.switchToSecondWindow(firstWindowHandle);

        helpPage.returnToMyBoards();

        int currentWindowsSize = helpPage.getHandlesSize();

        if (currentWindowsSize != windowsSizeAfter || !helpPage.getWindowHandle().equals(secondWindowHandle)) {
            helpPage.printSwitchErrorMessage();
            return;
        }

        Assert.assertTrue(helpPage.isCorrectPage(), "Switched on wrong page!");

    }
}
