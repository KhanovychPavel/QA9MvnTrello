package org.example.tests;

import org.example.pages.BoardsPageHelper;
import org.example.pages.CurrentBoardPageHelper;
import org.example.pages.LoginPageHelper;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


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
    public void newListCreatingTest() {
        String listTitle = "8";

        int listSizeInitially = qa9HaifaPage.listsQuantity();

        qa9HaifaPage.newListCreating(listTitle);

        int listsSizeAfterNewListCreating = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(listsSizeAfterNewListCreating, listSizeInitially + 1,
                "New list wasn't created");
    }

    @Test
    public void addNewCardTest() {
        String cardTitle = "newCardAdded";
        String listTitle = "anyList";

        qa9HaifaPage.getNewListCreatingIfNoLists (listTitle);

        int cardsQuantityBefore = qa9HaifaPage.cardsQuantity();

        qa9HaifaPage.addNewCardToTheFirstList(cardTitle);

        int cardsQuantityAfter = qa9HaifaPage.cardsQuantity();

        Assert.assertEquals(cardsQuantityAfter, cardsQuantityBefore + 1, "New list wasn't created");
    }

    @Test
    public void archiveFirstList() {
        String listTitle = "anyName";

        int sizeBeforeArchiving = qa9HaifaPage.getListSizeInitially(listTitle);

        qa9HaifaPage.getArchiveFirstList();

        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - 1, "List wasn't archived");
    }

    @Test
    public void copyFirstList() {
        String listTitle = "7";

        int sizeBeforeArchiving = qa9HaifaPage.getListSizeInitially(listTitle);

        qa9HaifaPage.getCreatingFirstListCopy();

        int sizeAfterCopying = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(sizeAfterCopying, sizeBeforeArchiving + 1, "No copy of the list has been created");

        //----- the name of copy is equals (is the same) to the name of the copied from -----
        /*Assert.assertEquals(qa9HaifaPage.textTitleCopy(0), qa9HaifaPage.textTitleOriginal(0 + 1),
                "No copy of the list has been created");*/
    }


    @Test
    public void archiveFirstListsNameContains() {
        String name = "7";

        int sizeBeforeArchiving = qa9HaifaPage.listSizeAfterListCreatingWithAbsentName(name);

        qa9HaifaPage.archivingListWithName(name);

        int sizeAfterArchiving = qa9HaifaPage.listsQuantity();

        Assert.assertEquals(sizeAfterArchiving, sizeBeforeArchiving - 1,
                "Lists with the required name have not been deleted");
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





