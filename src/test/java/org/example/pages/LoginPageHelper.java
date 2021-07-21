package org.example.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPageHelper extends PageBase {
    @FindBy(css = ".text-primary")
    WebElement logInIcon;
    @FindBy(id = "login")
    WebElement loginButton;
    @FindBy(id = "user")
    WebElement emailField;
    @FindBy(id = "password")
    WebElement passwordField;
    @FindBy(css = "p.error-message")
    WebElement errorMessage;
    @FindBy(css = ".btn-success")
    WebElement loginAsAttlButton;
    @FindBy(id = "login-submit")
    WebElement submitAsAttlButton;


    public LoginPageHelper(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPageHelper openPage() {
        waitUntilElementIsClickable(logInIcon, 40); // // wait for "Log in" is clickable
        logInIcon.click(); // find and click on "Log in"
        return this;
    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(loginButton,10); // wait for "Log in" button is is clickable ON THE LOG IN PAGE WITH EMAIL & PASSWORD FIELDS FILLING
        return this;
    }

    public void LoginNotAttl(String login, String password) {
        fillInEmailField(login);
        fillInPasswordField(password);
        submitLoginNotAtlassian();
    }

    public void loginAttl(String login, String password) {
        fillInEmailField(login);
        pressLoginAsAttlButton();
        fillInPasswordAttl(password);
        submitLoginAttl();
    }

    public String getErrorMessage () {
        waitUntilElementIsVisible(errorMessage, 10);
        return errorMessage.getText();
    }

    public void fillInEmailField (String value) {
//        WebElement emailField = driver.findElement(By.id("user"));
        editField(emailField, value);   // (emailField.click(); + emailField.sendKeys(".....");)
    }

    public void fillInPasswordField(String value) {
//        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, value);
    }

    public void submitLoginNotAtlassian() {
        loginButton.click(); // press "Log in" button
    }

    public void pressLoginAsAttlButton() {
        waitUntilElementIsClickable(loginAsAttlButton, 10); // wait for "Log in with Atlassian" button is clickable
        loginAsAttlButton.click(); // press "Log in with Atlassian"

    }

    public void fillInPasswordAttl(String value) {
        waitUntilElementIsClickable(submitAsAttlButton, 10); // wait for "Log in" button is clickable
        WebElement enterPassword = passwordField; // find the field "password"
        editField(enterPassword, value); // enter my own password & click

    }

    public void submitLoginAttl() {
        submitAsAttlButton.click(); // press Atlassian log in button

    }
}
