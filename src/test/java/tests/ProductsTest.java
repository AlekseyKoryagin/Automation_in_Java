package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pages.BasePage.GOODS_NAME;
import static user.UserFactory.withStandardPermission;

public class ProductsTest extends BaseTest {
    @Test
    public void checkFirstThingAddedToCart() {
        loginPage.open().login(withStandardPermission());
        assertTrue(productsPage.isPageTitleDisplayed(), "The products title didn't appear");
        productsPage.addFirstThingToCart();

        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), 1, "Incorrect count of thing in the cart");
        assertEquals(productsPage.navigationPanel.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Test
    public void checkAllThingsAddedToCart() {
        loginPage.open().login(withStandardPermission());
        assertTrue(productsPage.isPageTitleDisplayed(), "The products title didn't appear");
        productsPage.addAllThingsToCart();

        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), 6, "Incorrect count of thing in the cart");
        assertEquals(productsPage.navigationPanel.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Test
    public void checkAddingGoodsToCart() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoodsToCartFromList(GOODS_NAME);

        for (String goodsName : GOODS_NAME) {
            assertTrue(productsPage.isRightBorderColorBtn(goodsName, "px solid rgb(226, 35, 26)"),
                    "Incorrect border color of the Remote button");
        }
        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), GOODS_NAME.size(),
                "The quantity of the product added to the cart does not match the quantity on the cart link");
        assertEquals(productsPage.navigationPanel.getColorOfCountThingsInCartBadge(), "rgba(226, 35, 26, 1)",
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Test
    public void checkDeletingProduct() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoodsToCartFromList(GOODS_NAME);
        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), GOODS_NAME.size(),
                "The quantity of the product added to the cart does not match the quantity on the cart link");
        productsPage.deleteGoodsFromCartFromList(GOODS_NAME);

        assertEquals(productsPage.navigationPanel.getCountThingsInCartBadge(), "", "The Goods have not been deleted");
    }
}
