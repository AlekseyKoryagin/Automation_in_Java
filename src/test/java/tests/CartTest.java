package tests;

import org.testng.annotations.Test;
import pages.BasePage;

import java.util.List;

import static org.testng.Assert.*;

public class CartTest extends BaseTest {

    @Test
    public void checkGoToCart() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.clickCartLink();

        assertTrue(cartPage.isTitleDisplayed(), "The cart title didn't appear");
        assertEquals(cartPage.getTitle(), "Your Cart", "Incorrect page Title");
    }

    @Test
    public void checkGoodsIsAddedInCart() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.addGoodsToCartFromList(BasePage.GOODS_NAME).clickCartLink();
        List<String> goodsInCart = cartPage.getGoodsNames();

        assertFalse(cartPage.isListEmpty(goodsInCart));
        assertTrue(cartPage.isAddedGoodsInCart(goodsInCart, BasePage.GOODS_NAME), "The added product is not in the cart.");
    }

    @Test
    public void checkDeletingFromCart() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.addGoogsToCart(BasePage.GOODS_NAME.get(1)).clickCartLink();
        assertTrue(cartPage.isRemoveBtnDisplayed(BasePage.GOODS_NAME.get(1)), "The remove button did not appear");
        cartPage.deleteGoodsFromCart(BasePage.GOODS_NAME.get(1));

        assertTrue(cartPage.hasNotRemoveBtn(BasePage.GOODS_NAME.get(1)));
    }
}
