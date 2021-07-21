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
        waitUntilElementIsClickable(boardsIcon, 30); // wait for "Boards" button is clickable
        return this;
    }

    public String getBoardsButtonName() {
        return boardsIcon.getText();
    }

    public void boardsButtonInWorkspacesMenuClick() {
                    // to the "Board" in workspaces screen
        waitUntilElementIsClickable(boardsMenuLeft, 10); // wait the "Board" is clickable
        boardsMenuLeft.click(); // click the "Board"
        waitUntilElementIsVisible(headerYourWorkspace, 10); // wait menu "Your Workspace boards" is visible
    }

}
