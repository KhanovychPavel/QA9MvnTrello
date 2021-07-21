package org.example.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class HomePageTests extends TestBase {

    @Test
    public void verifyApplTest() {
        Assert.assertTrue(homePage.isCorrectPage());

    }
}
