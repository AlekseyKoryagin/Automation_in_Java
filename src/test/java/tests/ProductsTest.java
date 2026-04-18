package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {

    @Test
    public void checkLoginOut() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        productsPage.navigationPanel.clickBurgerMenu().clickLinkLogout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "The login button did not appear");
        assertEquals(loginPage.getUrl(), BasePage.BASE_URL, "Invalid URL after successful login");
    }

    @Test
    public void checkFirstThingAddedToCart() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        productsPage.addFirstThingToCart();

        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), "1", "Incorrect count of thing in the cart");
        assertEquals(productsPage.navigationPanel.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Test
    public void checkAllThingsAddedToCart() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        productsPage.addAllThingsToCart();

        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), "6", "Incorrect count of thing in the cart");
        assertEquals(productsPage.navigationPanel.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Test
    public void checkAddingGoodsToCart() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.addGoodsToCartFromList(BasePage.GOODS_NAME);

        for (String goodsName : BasePage.GOODS_NAME) {
            assertTrue(productsPage.isRightBorderColorBtn(goodsName, "px solid rgb(226, 35, 26)"),
                    "Incorrect border color of the Remote button");
        }
        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), String.valueOf(BasePage.GOODS_NAME.size()),
                "The quantity of the product added to the cart does not match the quantity on the cart link");
        assertEquals(productsPage.navigationPanel.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Test
    public void checkDeletingProduct() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.addGoodsToCartFromList(BasePage.GOODS_NAME);
        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), String.valueOf(BasePage.GOODS_NAME.size()),
                "The quantity of the product added to the cart does not match the quantity on the cart link");
        productsPage.deleteGoodsFromCartFromList(BasePage.GOODS_NAME);

        assertEquals(productsPage.navigationPanel.getCountThingsInCartBadge(), "", "The Goods have not been deleted");
    }
}
