package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static user.UserFactory.*;

@Epic("Проверка страницы Checkout Complete")
public class CheckoutCompleteTest extends BaseTest {
    @Story("Заказ оформлен успешно")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Issue("yandex.ru")
    @Test(description = "Проверка оформления заказа")
    public void checkMakeAnOrder() {
        loginPage.open().login(withStandardPermission());
        productsPage.addFirstThingToCart();
        productsPage.navigationPanel.clickCartLink();
        cartPage.clickCheckoutBtn();
        checkoutYourInformationPage.sendFilledForm(infoWithCorrectData());
        checkoutOverviewPage.clickFinishBtn();

        assertTrue(checkoutCompletePage.isTitleDisplayed(), "The title is not displayed.");
        assertEquals(checkoutCompletePage.getTitle(), "Checkout: Complete!", "The title is not correct.");
        assertTrue(checkoutCompletePage.isSuccessMessage(), "The success message is not displayed.");
        assertEquals(checkoutCompletePage.getSuccessMessage(), "Thank you for your order!", "The success message is not correct.");
        assertTrue(checkoutCompletePage.isBackHomeBtnDisplayed(), "The back home button is not displayed.");
    }
}
