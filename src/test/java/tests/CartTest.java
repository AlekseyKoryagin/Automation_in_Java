package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class CartTest extends BaseTest {

    @Test
    public void checkGoToCart() {
        loginPage.open();
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.clickCartLink();

        assertTrue(cartPage.isTitleDisplayed(), "The cart title didn't appear");
        assertEquals(cartPage.getTitle(), "Your Cart", "Incorrect page Title");
    }
}
