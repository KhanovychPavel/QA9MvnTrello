package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.CurrentBoardPageHelper;
import org.example.pages.LoginPageHelper;
import org.example.util.DataProviders;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.lang.reflect.Method;


public class CurrentBoardTests extends TestBase {
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa9HaifaPage;

    @BeforeMethod
    public void initTest() {
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa9HaifaPage = new CurrentBoardPageHelper(driver, "QA Haifa9");

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
    }

    @Test
    public void newListCreatingTest(Method method) {
        log4j.startTestCase("CurrentBoardTests - " + method.getName());
        String listTitle = "8";

        log4j.info("CurrentBoardTests - initial lists quantity");
        int listSizeInitially = qa9HaifaPage.listsQuantity();

        log4j.info("CurrentBoardTests - create a new list");
        qa9HaifaPage.newListCreating(listTitle);

        log4j.info("CurrentBoardTests - lists quantity after new list creating");
        int listsSizeAfterNewListCreating = qa9HaifaPage.listsQuantity();

        log4j.info("Assert: Expected condition has to be - number of lists after new list creating should increase" +
                " by 1");
        Assert.assertEquals(listsSizeAfterNewListCreating, listSizeInitially + 1,
                "New list wasn't created");
        log4j.endTestCase2();
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "newListCreating")
    public void newListCreatingTestParameter(String nameList) {
        log4j.startTestCase("newListCreatingTestParameter(), parameter: nameList = " + nameList + "");
//        String listTitle = "8";

        log4j.info("CurrentBoardTests - initial lists quantity");
        int listSizeInitially = qa9HaifaPage.listsQuantity();

        log4j.info("CurrentBoardTests - create a new list");
        qa9HaifaPage.newListCreating(nameList);

        log4j.info("CurrentBoardTests - lists quantity after new list creating");
        int listsSizeAfterNewListCreating = qa9HaifaPage.listsQuantity();

        log4j.info("Assert: Expected condition has to be - number of lists after new list creating should increase" +
                " by 1");
        Assert.assertEquals(listsSizeAfterNewListCreating, listSizeInitially + 1,
                "New list wasn't created");
        log4j.endTestCase2();
    }

    @Test
    public void addNewCardTest() {
        log4j.startTestCase("CurrentBoardTests - addNewCardTest()");
        String cardTitle = "newCardAdded";
        String listTitle = "anyList";

        log4j.info("CurrentBoardTests - new list creating if there are no lists on the board");
        qa9HaifaPage.getNewListCreatingIfNoLists (listTitle);

        log4j.info("CurrentBoardTests - initial cards quantity");
        int cardsQuantityBefore = qa9HaifaPage.cardsQuantity();

        log4j.info("CurrentBoardTests - add new card to the first list");
        qa9HaifaPage.addNewCardToTheFirstList(cardTitle);

        int cardsQuantityAfter = qa9HaifaPage.cardsQuantity();

        log4j.info("Assert: expected condition has to be: number of cards after adding a card should increase by 1" +
                "compared to number of cards before adding");
        Assert.assertEquals(cardsQuantityAfter, cardsQuantityBefore + 1, "New list wasn't created");
        log4j.endTestCase2();
    }

    @Test
    public void archiveFirstList() {
        log4j.startTestCase("CurrentBoardTests - archiveFirstList()");
        String listTitle = "anyName";
        log4j.info("CurrentBoardTests - initial lists quantity");
        int sizeBeforeArchiving = qa9HaifaPage.getListSizeInitially(listTitle);
        log4j.info("CurrentBoardTests - archive first list");
        qa9HaifaPage.getArchiveFirstList();
        log4j.info("CurrentBoardTests - number of lists after archiving");
        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();
        log4j.info("Assert: expected condition has to be: number of lists after archiving a list should decrease by 1" +
                " compared to number of lists before archiving");
        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - 1, "List wasn't archived");
        log4j.endTestCase2();
    }

    @Test
    public void copyFirstList() {
        log4j.startTestCase("CurrentBoardTests - copyFirstList()");
        String listTitle = "7";
        log4j.info("CurrentBoardTests - initial lists quantity");
        int sizeBeforeCopying = qa9HaifaPage.getListSizeInitially(listTitle);
        log4j.info("CurrentBoardTests - create first list copy");
        qa9HaifaPage.getCreatingFirstListCopy();
        log4j.info("CurrentBoardTests - lists quantity after copying");
        int sizeAfterCopying = qa9HaifaPage.listsQuantity();
        log4j.info("Assert: expected condition has to be: number of lists after copying a list should increase by 1" +
                " compared to number of lists before copying");
        Assert.assertEquals(sizeAfterCopying, sizeBeforeCopying + 1, "No copy of the list has been created");

        //----- the name of copy is equals (is the same) to the name of the copied from -----
        /*Assert.assertEquals(qa9HaifaPage.textTitleCopy(0), qa9HaifaPage.textTitleOriginal(0 + 1),
                "No copy of the list has been created");*/
        log4j.endTestCase2();
    }


    @Test
    public void archiveFirstListsNameContains() {
        log4j.startTestCase("CurrentBoardTests - archiveFirstListsNameContains()");
        String name = "7";
        log4j.info("CurrentBoardTests - number of lists initial");
        int sizeBeforeArchiving = qa9HaifaPage.listSizeAfterListCreatingWithAbsentName(name);
        log4j.info("CurrentBoardTests - archive first list with required name");
        qa9HaifaPage.archivingListWithName(name);
        log4j.info("CurrentBoardTests - number of lists after archiving");
        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();
        log4j.info("Assert: expected condition has to be: number of lists after archiving a list should decrease by 1" +
                " compared to number of lists before archiving);");
        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - 1,
                "Lists with the required name have not been deleted");
        log4j.endTestCase2();
    }
}




//----- Archiving all elements with required name from the list without creation new list with required name if there are not a single one with required name ----
//    @Test(priority = 6, dependsOnMethods = "copyFirstList")
//    public void archiveListsNameContains2() {
//        String name = "7";
//        List<WebElement> list = qa9HaifaPage.getListsList();
//        int sizeBeforeArchiving = qa9HaifaPage.listsQuantity();
//        int count = 0;
//
//        for (WebElement element : list) {
//            if (element.findElement(By.cssSelector(".js-list-name-input")).getAttribute("aria-label").equals(name)) {
//                element.findElement(By.cssSelector(".list-header-extras")).click();
//                qa9HaifaPage.chooseArchiveThisList();
//                count++;
//                return;
//            }
//        }
//        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();
//
//        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - count,
//                "Lists with the required name have not been deleted");
//    }





