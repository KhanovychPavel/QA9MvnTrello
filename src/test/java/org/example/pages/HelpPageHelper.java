package org.example.pages;


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

    public String getWindowHandle() {
        return driver.getWindowHandle();
    }

    public HelpPageHelper waitUntilPageIsLoaded() {
        String firstWindowHandle = getWindowHandle();
        waitUntilWindowsToBe(2,20);
        String secondWindowHandle = getAnotherWindowHandle(firstWindowHandle);
        driver.switchTo().window(secondWindowHandle);
        waitUntilElementIsClickable(goToYourBoardsButton, 5);
        return this;
    }

    public String getHelpPageTagName() {
        return helpPageTagName.getText();
    }

    public void closeWindowByPressGoToYourBoardButton() {
        goToYourBoardsButton.click();
    }

    public void closeHelpPageAndSwitchToPreviousPage() {
        String currentWindowHandle = driver.getWindowHandle();
        String anotherWindowHandle = getAnotherWindowHandle(currentWindowHandle);
        closeCurrentWindow();
        driver.switchTo().window(anotherWindowHandle);
    }




    public WebDriver switchToSecondWindow(String firstWindowHandle) {
        return driver.switchTo().window(getAnotherWindowHandle(firstWindowHandle));
    }

    public int getHandlesSize() {
        return driver.getWindowHandles().size();
    }

    public void printOpeningErrorMessage() {
        System.out.println("Error! Opening of the page isn't correct!");
    }

    public void printSwitchErrorMessage() {
        System.out.println("Error! Switch to the page isn't correct!");
    }
}
