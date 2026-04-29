package tests;

import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static pages.BasePage.GOODS_NAME;
import static user.UserFactory.withStandardPermission;

public class CartTest extends BaseTest {
    @Test
    public void checkGoodsIsAddedInCart() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoodsToCartFromList(GOODS_NAME).navigationPanel.clickCartLink();
        List<String> goodsInCart = cartPage.getGoodsNames();

        assertFalse(cartPage.isListEmpty(goodsInCart));
        assertTrue(cartPage.isAddedGoodsInCart(goodsInCart, GOODS_NAME), "The added product is not in the cart.");
    }

    @Test(invocationCount = 3)
    public void checkDeletingFromCart() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoogsToCart(GOODS_NAME.get(1)).navigationPanel.clickCartLink();
        assertTrue(cartPage.isRemoveBtnDisplayed(GOODS_NAME.get(1)), "The remove button did not appear");
        cartPage.deleteGoodsFromCart(GOODS_NAME.get(1));

        assertTrue(cartPage.hasNotRemoveBtn(GOODS_NAME.get(1)));
    }
}
