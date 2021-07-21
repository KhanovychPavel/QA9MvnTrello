package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HelpPageHelper extends PageBase {
    @FindBy(tagName = "h1")
    WebElement helpPageTagName;
    @FindBy(xpath = "//span[contains(text(),'Go to your boards')]")
    WebElement goToYourBoardsButton;
    @FindBy(xpath = "(//button[@data-test-id='header-boards-menu-button']/span)[2]")
    WebElement boardsIcon;


    public HelpPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public String getHelpPageTagName() {
        return helpPageTagName.getText();
    }

    public void printOpeningErrorMessage() {
        System.out.println("Error! Opening of the page isn't correct!");
    }

    public String getSecondWindowHandle(String firstWindowHandle) {
        String secondWindowHandle = "";
        for (String handle : driver.getWindowHandles()) {
            if (!handle.equals(firstWindowHandle)) {
                secondWindowHandle = handle;
            }
        }
        return secondWindowHandle;
    }

    public void returnToMyBoards() {
        waitUntilElementIsClickable(goToYourBoardsButton, 5);
        goToYourBoardsButton.click();
        waitUntilElementIsClickable(boardsIcon, 30);
    }

    public void printSwitchErrorMessage() {
        System.out.println("Error! Switch to the page isn't correct!");
    }

    public boolean isCorrectPage() {
        return boardsIcon.getText().equals("Boards");
    }

}
