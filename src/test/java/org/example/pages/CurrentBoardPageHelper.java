package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;


public class CurrentBoardPageHelper extends PageBase {
    @FindBy(css = ".placeholder")
    WebElement addAListButton;
    @FindBy(css = ".js-list-content")
    List<WebElement> collumnsList;
    @FindBy(css = ".list-name-input")
    WebElement enterListTitle;
    @FindBy(css = ".js-save-edit")
    WebElement saveListButton;
    @FindBy(css = ".js-cancel-edit")
    WebElement cancelNewListButton;
    @FindBy(css = ".js-card-name")
    List<WebElement> cardsList;
    @FindBy(css = ".card-composer-container")
    WebElement addACard;
    @FindBy(css = ".js-card-title")
    WebElement cardTitleField;
    @FindBy(css = ".js-add-card")
    WebElement addCard;
    @FindBy(css = ".js-cancel")
    WebElement cancelNewCardButton;
    @FindBy(css = ".list-header-extras-menu")
    WebElement listActionsButton;
    @FindBy(css = ".js-close-list")
    WebElement archiveThisListButton;
    @FindBy(css = ".js-copy-list")
    WebElement copyListButton;
    @FindBy(css = ".js-submit")
    WebElement createCopyListSubmit;
    @FindBy(css = ".js-autofocus")
    WebElement copyListNameField;
    @FindBy(css = ".list-header-name")
    List<WebElement> listsTitles;
    @FindBy(tagName = "h1")
    WebElement currentBoardName;


    String boardName;

    public CurrentBoardPageHelper(WebDriver driver, String boardName) {
        this.driver = driver;
        this.boardName = boardName;
        PageFactory.initElements(driver, this);
    }

    public CurrentBoardPageHelper openPage() {
        // dynamic element - no fix!!!
        log4j.startMethod("CurrentBoardPageHelper - openPage()");
        log4j.info("CurrentBoardPageHelper - wait 'QA Haifa9' board is clickable");
        waitUntilElementIsClickable(getLocatorBoardButton(), 20); // wait 'QA Haifa9' board is clickable
        log4j.info("CurrentBoardPageHelper - click on 'QA Haifa9' board");
        driver.findElement(getLocatorBoardButton()).click(); // click on 'QA Haifa9' board
        log4j.endMethod("CurrentBoardPageHelper - openPage()");
        return this;
    }

    public By getLocatorBoardButton() {
        log4j.startMethod("CurrentBoardPageHelper - getLocatorBoardButton()");
        log4j.endMethod("CurrentBoardPageHelper - getLocatorBoardButton()");
        return By.xpath("//a[@class = 'board-tile'][.//div[@title='" + boardName + "']]");
    }

    public void waitUntilPageIsLoaded() {
        log4j.startMethod("CurrentBoardPageHelper - waitUntilPageIsLoaded()");
        log4j.info("CurrentBoardPageHelper - wait for add list is clickable");
        waitUntilElementIsClickable(addAListButton, 20); // wait for add list is clickable
        log4j.info("CurrentBoardPageHelper - wait for all lists elements are visible");
        if (listsQuantity() != 0) {
            waitUntilAllElementsAreVisible(collumnsList, 10); // wait for all lists elements are present
        }
        log4j.endMethod("CurrentBoardPageHelper - waitUntilPageIsLoaded()");
    }

    public int listsQuantity() {
        log4j.startMethod("CurrentBoardPageHelper - listsQuantity()");
        log4j.endMethod("CurrentBoardPageHelper - listsQuantity()");
        return collumnsList.size();
    }

    public void getNewListCreatingIfNoLists(String listTitle) {
        log4j.startMethod("CurrentBoardPageHelper - getNewListCreatingIfNoLists()");
        log4j.info("CurrentBoardPageHelper - initial lists quantity");
        int listSizeInitially = listsQuantity();
        log4j.info("CurrentBoardPageHelper - new list creating if lists quantity = 0");
        if(listSizeInitially == 0) {
            newListCreating(listTitle);
        }
        log4j.endMethod("CurrentBoardPageHelper - getNewListCreatingIfNoLists()");
    }

    public int getListSizeInitially(String listTitle) {
        log4j.startMethod("CurrentBoardPageHelper - getListSizeInitially()");
        log4j.info("CurrentBoardPageHelper - initial lists quantity");
        int listSizeInitially = listsQuantity();
        log4j.info("CurrentBoardPageHelper - new list creating if lists quantity = 0");
        if (listSizeInitially == 0) {
            newListCreating(listTitle);
            listSizeInitially++;
        }
        log4j.endMethod("CurrentBoardPageHelper - getListSizeInitially()");
        return listSizeInitially;
    }

    public void newListCreating(String listTitle) {
        addNewList();
        enterNewListTitle(listTitle);
        createNewListConfirm();
        cancelElseOneNewListAdding();
    }

    public void addNewList() {
        log4j.startMethod("CurrentBoardPageHelper - addNewList()");
        log4j.info("CurrentBoardPageHelper - press 'Add a list' button");
        addAListButton.click(); // press 'Add a list' button
        log4j.endMethod("CurrentBoardPageHelper - addNewList()");
    }

    public void enterNewListTitle(String listTitle) {
        log4j.startMethod("CurrentBoardPageHelper - enterNewListTitle()");
        log4j.info("CurrentBoardPageHelper - wait 'add list' button is clickable");
        waitUntilElementIsClickable(enterListTitle, 10); // wait "add list" button is clickable
        log4j.info("CurrentBoardPageHelper - fill name of the list field");
        editField(enterListTitle, listTitle); // enter name of the list
        log4j.endMethod("CurrentBoardPageHelper - enterNewListTitle()");
    }

    public void createNewListConfirm() {
        log4j.startMethod("CurrentBoardPageHelper - createNewListConfirm()");
        log4j.info("CurrentBoardPageHelper - lists quantity before new list creating");
        int sizeBeforeNewListCreating = listsQuantity();
        log4j.info("CurrentBoardPageHelper - wait until save list button is clickable");
        waitUntilElementIsClickable(saveListButton, 5);
        log4j.info("CurrentBoardPageHelper - click 'Add list' button");
        saveListButton.click(); // click 'Add list' button
        log4j.info("CurrentBoardPageHelper - wait until quantity of the lists increases by 1");
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), sizeBeforeNewListCreating + 1, 10);
        log4j.endMethod("CurrentBoardPageHelper - createNewListConfirm()");
    }

    public void cancelElseOneNewListAdding() {
        log4j.startMethod("CurrentBoardPageHelper - cancelElseOneNewListAdding()");
        log4j.info("CurrentBoardPageHelper - wait until cancel new list button is clickable ('x')");
        waitUntilElementIsClickable(cancelNewListButton, 10); // wait the 'x' button to cancel new list creating is clickable
        log4j.info("CurrentBoardPageHelper - click on cancel new list button ('x')");
        cancelNewListButton.click();
//        waitUntilElementIsInvisible((By.cssSelector(".list-name-input")), 10); // wait 'enter list title' field going to be invisible
        log4j.endMethod("CurrentBoardPageHelper - cancelElseOneNewListAdding()");
    }


    public int cardsQuantity() {
        log4j.startMethod("CurrentBoardPageHelper - cardsQuantity()");
        log4j.endMethod("CurrentBoardPageHelper - cardsQuantity()");
        return cardsList.size();
    }


    public void addNewCardToTheFirstList(String cardTitle) {
        log4j.startMethod("CurrentBoardPageHelper - addNewCardToTheFirstList()");
        int cardListSizeBeforeAdding = cardsQuantity();
        log4j.info("CurrentBoardPageHelper - press 'Add a card button'");
        addACard.click(); // pressAddACardButton
        log4j.info("CurrentBoardPageHelper - wait 'enter a title for this card...' is clickable");
        waitUntilElementIsClickable(cardTitleField, 5); // wait "enter a title for this card..." is clickable
        log4j.info("CurrentBoardPageHelper - fill the field 'enter a title for this card...'");
        editField(cardTitleField, cardTitle); //enterNewCardTitle
        log4j.info("CurrentBoardPageHelper - press the add card button (submit adding card button)");
        addCard.click(); // pressAddCardButton (submit card button)
        log4j.info("CurrentBoardPageHelper - wait until number of cards in card list increases by 1");
        waitUntilElementsBecome(By.cssSelector(".js-card-name"), cardListSizeBeforeAdding + 1, 10);
        log4j.info("CurrentBoardPageHelper - wait until 'x' button is clickable");
        waitUntilElementIsClickable(cancelNewCardButton, 10); // wait 'x' button is clickable
        log4j.info("CurrentBoardPageHelper - click 'x' button");
        cancelNewCardButton.click(); // click 'x' button
        log4j.endMethod("CurrentBoardPageHelper - addNewCardToTheFirstList()");
    }

    public void getArchiveFirstList() {
        log4j.startMethod("CurrentBoardPageHelper - getArchiveFirstList()");
        log4j.info("CurrentBoardPageHelper - initial lists quantity");
        int sizeBeforeArchiving = listsQuantity();
        log4j.info("CurrentBoardPageHelper - click on list action button");
        listActionsButton.click(); // pressFirstListActionsButton
        log4j.info("CurrentBoardPageHelper - wait until 'archive this list' button is clickable");
        waitUntilElementIsClickable(archiveThisListButton, 10);
        log4j.info("CurrentBoardPageHelper - click on 'archive this list' button");
        archiveThisListButton.click(); // chooseArchiveThisList
        log4j.info("CurrentBoardPageHelper - wait until number of the lists decrease by 1");
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), sizeBeforeArchiving - 1, 10);
        log4j.endMethod("CurrentBoardPageHelper - getArchiveFirstList()");
    }

    public void getCreatingFirstListCopy() {
        log4j.startMethod("CurrentBoardPageHelper - getCreatingFirstListCopy()");
        log4j.info("CurrentBoardPageHelper - click on list action button");
        listActionsButton.click();
        log4j.info("CurrentBoardPageHelper - choose copy list directory");
        chooseCopyList();
        log4j.info("CurrentBoardPageHelper - confirm copy of the list");
        createCopyListConfirm();
        log4j.endMethod("CurrentBoardPageHelper - getCreatingFirstListCopy()");
    }

    public void chooseCopyList() {
        log4j.startMethod("CurrentBoardPageHelper - chooseCopyList()");
        log4j.info("CurrentBoardPageHelper - wait until copy list button is visible");
        waitUntilElementIsVisible(copyListButton, 10);
        log4j.info("CurrentBoardPageHelper - click on copy list button");
        copyListButton.click();
        log4j.endMethod("CurrentBoardPageHelper - chooseCopyList()");
    }

    public void createCopyListConfirm() {
        log4j.startMethod("CurrentBoardPageHelper - createCopyListConfirm()");
        log4j.info("CurrentBoardPageHelper - initial lists quantity");
        int sizeBeforeCopying = listsQuantity();
        log4j.info("CurrentBoardPageHelper - wait until 'Create list' button is clickable");
        waitUntilElementIsClickable(createCopyListSubmit, 5); // waiting for "Create list" button clickable
        log4j.info("CurrentBoardPageHelper - click on list name field");
        copyListNameField.click(); // click on list name field
        log4j.info("CurrentBoardPageHelper - submit creating of list's copy");
        createCopyListSubmit.click();
        waitUntilElementsBecome(By.cssSelector(".js-list-content"), sizeBeforeCopying + 1, 10);
        log4j.info("CurrentBoardPageHelper - wait until number of lists increases by 1");
        log4j.endMethod("CurrentBoardPageHelper - createCopyListConfirm()");
    }

    public List<WebElement> getListsTitles() {
        log4j.startMethod("CurrentBoardPageHelper - getListsTitles()");
        log4j.endMethod("CurrentBoardPageHelper - getListsTitles()");
        return listsTitles;
    }

    public List<WebElement> getListsList() {
        log4j.startMethod("CurrentBoardPageHelper - getListsList()");
        log4j.endMethod("CurrentBoardPageHelper - getListsList()");
        return collumnsList;
    }

    public void archivingListWithName(String name) {
        log4j.startMethod("CurrentBoardPageHelper - archivingListWithName()");
        log4j.info("CurrentBoardPageHelper - initial lists quantity");
        int sizeBeforeArchiving = listsQuantity();
        log4j.info("CurrentBoardPageHelper - archiving list with required name");
        List<WebElement> list = getListsList();
        log4j.info("CurrentBoardPageHelper - finding list with required name");
        for (WebElement element : list) {
            if (element.findElement(By.cssSelector(".js-list-name-input"))
                    .getAttribute("aria-label").equals(name)) {
                log4j.info("CurrentBoardPageHelper - find and click extra menu of list with required name");
                element.findElement(By.cssSelector(".list-header-extras-menu")).click();
                log4j.info("CurrentBoardPageHelper - wait until 'archive this list button' is clickable");
                waitUntilElementIsClickable(archiveThisListButton, 10);
                log4j.info("CurrentBoardPageHelper - click on 'archive this list button'");
                archiveThisListButton.click(); // chooseArchiveThisList
                log4j.info("CurrentBoardPageHelper - wait until number of the lists decrease by 1");
                waitUntilElementsBecome(By.cssSelector(".js-list-content"), sizeBeforeArchiving - 1, 10);
                return;
            }
        }

        log4j.endMethod("CurrentBoardPageHelper - archivingListWithName()");
    }

    public int listSizeAfterListCreatingWithAbsentName(String name) {
        log4j.startMethod("CurrentBoardPageHelper - listSizeAfterListCreatingWithAbsentName");
        log4j.info("CurrentBoardPageHelper - initial lists quantity");
        int sizeBeforeArchiving = listsQuantity();
        log4j.info("CurrentBoardPageHelper - new list creating if there is no list with required name");
        List<WebElement> elements = getListsTitles();
        if (elements.isEmpty() || getNumberOfListsWithName(name) == 0) {
            log4j.info("CurrentBoardPageHelper - new list creating");
            newListCreating(name);
            log4j.info("CurrentBoardPageHelper - initial lists quantity after new list creating (size before archiving)");
            sizeBeforeArchiving++;
        }
        log4j.endMethod("CurrentBoardPageHelper - listSizeAfterListCreatingWithAbsentName");
        return sizeBeforeArchiving;
    }

    public int getNumberOfListsWithName(String name) {
        log4j.startMethod("CurrentBoardPageHelper - getNumberOfListsWithName");
        List<WebElement> listsTitles = getListsTitles();
        int count = 0;
        log4j.info("CurrentBoardPageHelper - calculation number of the lists with required name");
        for (WebElement element : listsTitles) {
            if (element.getText().equals(name)) {
                count++;
            }
        }
        log4j.endMethod("CurrentBoardPageHelper - getNumberOfListsWithName");
        return count;
    }

    public String textTitleCopy(int value) {
        return listsTitles.get(value).getText();
    }

    public String textTitleOriginal(int value) {
        return listsTitles.get(value - 1).getText();
    }


    public boolean isCorrectPage() {
        return currentBoardName.getText().equals(getBoardName());
    }

    public String getBoardName() {
        return boardName;
    }
}


/*
    public int getNumberOfElements(String name) {
        List<WebElement> titleList = getListsTitles();
        int number = -1;
        int count = 0;
        for (WebElement element : titleList) {
            if (element.getText().equals(name)) {
                number = count;
            }
            count++;
        }
        return number;


    }*/

