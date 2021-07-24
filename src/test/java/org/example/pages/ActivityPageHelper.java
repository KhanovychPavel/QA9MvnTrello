package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class ActivityPageHelper extends PageBase {
    @FindBy(css = ".phenom-desc")
    List <WebElement> activityList;


    public ActivityPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public void waitUntilPageIsLoaded() {
        waitUntilAllElementsAreVisible(activityList, 5);
    }

    public String textReceiveLastListAdded() {
        return activityList.get(0).getText();
    }

    public boolean textReceiveLastListAddedFromActivity(String listTitle) {
        boolean res = false;
        for(WebElement element : activityList) {
            if(element.getText().contains(listTitle)) {
                res = true;
            }
        }
        return res;
    }

    public void printChars() {

    }
}
