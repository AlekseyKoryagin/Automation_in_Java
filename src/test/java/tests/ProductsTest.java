package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test
    public void checkLoginOut() {
        loginPage.open();
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        productsPage.clickBurgerMenu();
        productsPage.clickLinkLogout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "The login button did not appear");
        assertEquals(loginPage.getUrl(), BasePage.BASE_URL,
                "Invalid URL after successful login");
    }

    @Test
    public void checkFirstThingAddedToCart() {
        loginPage.open();
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        productsPage.addFirstThingToCart();

        assertEquals(productsPage.getCountThingsInCart(), "1", "Incorrect count of thing in the cart");
        assertEquals(productsPage.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Test
    public void checkAllThingsAddedToCart() {
        loginPage.open();
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        productsPage.addAllThingsToCart();

        assertEquals(productsPage.getCountThingsInCart(), "6", "Incorrect count of thing in the cart");
        assertEquals(productsPage.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }
}
