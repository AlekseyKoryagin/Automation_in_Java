package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pages.BasePage.GOODS_NAME;
import static user.UserFactory.withStandardPermission;

@Epic("Проверка страницы Товары")
public class ProductsTest extends BaseTest {
    @Feature("Добавление товаров")
    @Story("Проверка добавления первого товара в выборке")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка добавления первого товара в выборке", enabled = false)
    public void checkFirstThingAddedToCart() {
        loginPage.open().login(withStandardPermission());
        assertTrue(productsPage.isTitleDisplayed(), "The products title didn't appear");
        productsPage.addFirstThingToCart();

        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), 1, "Incorrect count of thing in the cart");
        assertTrue(productsPage.navigationPanel.isRightColorOfCountThingsInCartBadge("226, 35, 26"),
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Feature("Добавление товаров")
    @Story("Проверка добавления всех товаров в выборке")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Issue("yandex.ru")
    @Test(description = "Проверка добавления всех товаров в выборке")
    public void checkAllThingsAddedToCart() {
        loginPage.open().login(withStandardPermission());
        assertTrue(productsPage.isTitleDisplayed(), "The products title didn't appear");
        productsPage.addAllThingsToCart();

        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), 6, "Incorrect count of thing in the cart");
        assertTrue(productsPage.navigationPanel.isRightColorOfCountThingsInCartBadge("226, 35, 26"),
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Feature("Добавление товаров")
    @Story("Проверка добавления товаров из списка товаров")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка добавления товаров из списка товаров", priority = 2)
    public void checkAddingGoodsToCart() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoodsToCartFromList(GOODS_NAME);

        for (String goodsName : GOODS_NAME) {
            assertTrue(productsPage.isRightBorderColorBtn(goodsName, "226, 35, 26"),
                    "Incorrect border color of the Remote button");
        }
        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), GOODS_NAME.size(),
                "The quantity of the product added to the cart does not match the quantity on the cart link");
        assertTrue(productsPage.navigationPanel.isRightColorOfCountThingsInCartBadge("226, 35, 26"),
                "The color of the icon with the number of items in the basket is incorrect");
    }

    @Feature("Удаление товаров")
    @Story("Проверка удаления добавленных товаров из списка")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка удаления добавленных товаров из списка", priority = 2)
    public void checkDeletingProduct() {
        loginPage.open().login(withStandardPermission());
        productsPage.addGoodsToCartFromList(GOODS_NAME);
        assertEquals(productsPage.navigationPanel.getCountThingsInCart(), GOODS_NAME.size(),
                "The quantity of the product added to the cart does not match the quantity on the cart link");
        productsPage.deleteGoodsFromCartFromList(GOODS_NAME);

        assertEquals(productsPage.navigationPanel.getCountThingsInCartBadge(), "", "The Goods have not been deleted");
    }
}
