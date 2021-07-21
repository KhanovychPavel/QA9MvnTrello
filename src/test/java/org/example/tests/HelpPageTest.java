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
    public void helpWindowTests() throws InterruptedException {
        String firstWindowHandle = driver.getWindowHandle();
        int windowsSizeBefore = driver.getWindowHandles().size();
        menuPage.openHelpPage();
        Thread.sleep(2000);
        int windowsSizeAfter = driver.getWindowHandles().size();

        if(windowsSizeAfter != windowsSizeBefore + 1) {
            helpPage.printOpeningErrorMessage();
            return;
        }
            driver.switchTo().window(helpPage.getSecondWindowHandle(firstWindowHandle));

        Assert.assertEquals(helpPage.getHelpPageTagName(),"Get help with Trello",
                "Wrong page was opened");
    }

    @Test
    public void goToYourBoards() throws InterruptedException {
        String firstWindowHandle = driver.getWindowHandle();
        int windowsSizeBefore = driver.getWindowHandles().size();
        menuPage.openHelpPage();
        Thread.sleep(2000);
        int windowsSizeAfter = driver.getWindowHandles().size();

        if(windowsSizeAfter != windowsSizeBefore + 1) {
            helpPage.printOpeningErrorMessage();
            return;
        }
        String secondWindowHandle = helpPage.getSecondWindowHandle(firstWindowHandle);
        driver.switchTo().window(secondWindowHandle);

        helpPage.returnToMyBoards();

        int currentWindowsSize = driver.getWindowHandles().size();

        if (currentWindowsSize != windowsSizeAfter || !driver.getWindowHandle().equals(secondWindowHandle)) {
            helpPage.printSwitchErrorMessage();
            return;
        }

        Assert.assertTrue(helpPage.isCorrectPage(), "Switched on wrong page!");

    }
}
