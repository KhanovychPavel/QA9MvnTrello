package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPageHelper extends PageBase {
    @FindBy(xpath = "(//button[@data-test-id='header-boards-menu-button']/span)[2]")
    WebElement boardsIcon;
    @FindBy(xpath = "//a[@data-test-id = 'home-team-boards-tab']")
    WebElement boardsMenuLeft;
    @FindBy(xpath = "//h3[contains(text(),'Your Workspace boards')]")
    WebElement headerYourWorkspace;

    public BoardsPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public BoardsPageHelper waitUntilPageIsLoaded() {
        log4j.startMethod("BoardsPageHelper - waitUntilPageIsLoaded()");
        log4j.info("BoardsPageHelper - wait for 'Boards' button is clickable");
        waitUntilElementIsClickable(boardsIcon, 30); // wait for "Boards" button is clickable
        log4j.endMethod("BoardsPageHelper - waitUntilPageIsLoaded()");
        return this;
    }

    public String getBoardsButtonName() {
        return boardsIcon.getText();
    }

    public void boardsButtonInWorkspacesMenuClick() {
                    // to the "Board" in workspaces screen
        log4j.startMethod("BoardsPageHelper - boardsButtonInWorkspacesMenuClick()");
        log4j.info("BoardsPageHelper - wait the 'Board' is clickable");
        waitUntilElementIsClickable(boardsMenuLeft, 10); // wait the "Board" is clickable
        log4j.info("BoardsPageHelper - click the 'Board'");
        boardsMenuLeft.click(); // click the "Board"
        log4j.info("BoardsPageHelper - wait menu 'Your Workspace boards' is visible");
        waitUntilElementIsVisible(headerYourWorkspace, 10); // wait menu "Your Workspace boards" is visible
        log4j.endMethod("BoardsPageHelper - boardsButtonInWorkspacesMenuClick()");
    }

    public boolean isCorrectPage() {
        return getBoardsButtonName().equals("Boards");
    }

}
