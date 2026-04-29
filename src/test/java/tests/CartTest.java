package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.*;
import static pages.BasePage.GOODS_NAME;
import static user.UserFactory.withStandardPermission;

@Epic("Проверка страницы корзина")
public class CartTest extends BaseTest {
    @Story("Добавление товара в корзину")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Issue("yandex.ru")
    @Test(description = "Проверка добавленного товара в корзину")
    public void checkGoodsIsAddedInCart() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoodsToCartFromList(GOODS_NAME).navigationPanel.clickCartLink();
        List<String> goodsInCart = cartPage.getGoodsNames();

        assertFalse(cartPage.isListEmpty(goodsInCart));
        assertTrue(cartPage.isAddedGoodsInCart(goodsInCart, GOODS_NAME), "The added product is not in the cart.");
    }

    @Story("Удаление товара из корзины")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка удаления товара в корзине", invocationCount = 3)
    public void checkDeletingFromCart() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoogsToCart(GOODS_NAME.get(1)).navigationPanel.clickCartLink();
        assertTrue(cartPage.isRemoveBtnDisplayed(GOODS_NAME.get(1)), "The remove button did not appear");
        cartPage.deleteGoodsFromCart(GOODS_NAME.get(1));

        assertTrue(cartPage.hasNotRemoveBtn(GOODS_NAME.get(1)));
    }
}
