package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MenuPageHelper extends PageBase {
    @FindBy(css = ".js-open-header-member-menu")
    WebElement headerMenuButton;
    @FindBy(xpath = "//a[@data-test-id='header-member-menu-profile']")
    WebElement profileAndVisibilityItem;
    @FindBy(xpath = "(//span[contains (text(), 'Activity')])[2]")
    WebElement activityItem;
    @FindBy(xpath = "//span[contains(text(),'Help')]")
    WebElement helpItem;


    public MenuPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public MenuPageHelper openPage() {
        headerMenuButton.click();
        return this;
    }

    public MenuPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(profileAndVisibilityItem, 10);
        return this;
    }

    public String getProfileVisibilityMenuName() {
        return profileAndVisibilityItem.getText();
    }

    public MenuPageHelper openActivityPage() {
        activityItem.click();
        return this;
    }

    public MenuPageHelper openHelpPage() {
        helpItem.click();
        return this;
    }

}
