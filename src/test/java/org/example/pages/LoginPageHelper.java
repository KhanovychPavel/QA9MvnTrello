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
        log4j.startMethod("LoginPageHelper - openPage()");
        log4j.info("wait until loginIcon button is clickable and click on it");
        waitUntilElementIsClickable(logInIcon, 40); // // wait for "Log in" is clickable
        logInIcon.click(); // find and click on "Log in"
        log4j.endMethod("LoginPageHelper - openPage()");
        return this;
    }

    public LoginPageHelper waitUntilPageIsLoaded() {
        log4j.startMethod("LoginPageHelper - waitUntilPageIsLoaded()");
        log4j.info("Wait until 'login' button is clickable");
        waitUntilElementIsClickable(loginButton,10); // wait for "Log in" button is is clickable ON THE LOG IN PAGE WITH EMAIL & PASSWORD FIELDS FILLING
        log4j.endMethod("LoginPageHelper - waitUntilPageIsLoaded()");
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
        log4j.startMethod("LoginPageHelper - getErrorMessage()");
        log4j.info("wait until error message is visible");
        waitUntilElementIsVisible(errorMessage, 10);
        log4j.endMethod("LoginPageHelper - getErrorMessage()");
        log4j.info("return error message");
        return errorMessage.getText();
    }

    public void fillInEmailField (String value) {
        log4j.startMethod("LoginPageHelper - fillInEmailField()");
//        WebElement emailField = driver.findElement(By.id("user"));
        log4j.info("LoginPageHelper - fill email field and click");
        editField(emailField, value);   // (emailField.click(); + emailField.sendKeys(".....");)
        log4j.endMethod("LoginPageHelper - fillInEmailField()");
    }

    public void fillInPasswordField(String value) {
//        WebElement passwordField = driver.findElement(By.id("password"));
        editField(passwordField, value);
    }

    public void submitLoginNotAtlassian() {
        loginButton.click(); // press "Log in" button
    }

    public void pressLoginAsAttlButton() {
        log4j.startMethod("LoginPageHelper - pressLoginAsAttlButton()");
        log4j.info("LoginPageHelper - wait for 'Log in with Atlassian' button is clickable");
        waitUntilElementIsClickable(loginAsAttlButton, 10); // wait for "Log in with Atlassian" button is clickable
        log4j.info("LoginPageHelper - press 'Log in with Atlassian'");
        loginAsAttlButton.click(); // press "Log in with Atlassian"
        log4j.endMethod("LoginPageHelper - pressLoginAsAttlButton()");
    }

    public void fillInPasswordAttl(String value) {
        log4j.startMethod("LoginPageHelper - fillInPasswordAttl()");
        log4j.info("LoginPageHelper - wait for 'Log in' button is clickable");
        waitUntilElementIsClickable(submitAsAttlButton, 10); // wait for "Log in" button is clickable
        log4j.info("LoginPageHelper - find the field 'password'");
        WebElement enterPassword = passwordField; // find the field "password"
        log4j.info("LoginPageHelper - enter my own password and click");
        editField(enterPassword, value); // enter my own password & click
        log4j.endMethod("LoginPageHelper - fillInPasswordAttl()");
    }

    public void submitLoginAttl() {
        log4j.startMethod("LoginPageHelper - submitLoginAttl()");
        log4j.info("LoginPageHelper - press Atlassian log in button");
        submitAsAttlButton.click(); // press Atlassian log in button
        log4j.endMethod("LoginPageHelper - submitLoginAttl()");
    }
}
