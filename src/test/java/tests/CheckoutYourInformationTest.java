package tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import user.UserData;

import static org.testng.Assert.*;
import static user.UserFactory.*;

@Epic("Проверка страницы Checkout Your Information")
public class CheckoutYourInformationTest extends BaseTest {
    @Story("Проверка перехода к оформлению заказа, отображения элементов")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка перехода к оформлению заказа, отображения элементов")
    public void checkGoToCheckout() {
        loginPage.open().login(withStandardPermission());
        productsPage.navigationPanel.clickCartLink();
        cartPage.clickCheckoutBtn();

        soft.assertTrue(checkoutYourInformationPage.isTitleDisplayed(), "The title did not appear.");
        soft.assertEquals(checkoutYourInformationPage.getTitle(), "Checkout: Your Information", "Incorrect checkout page name");

        soft.assertTrue(checkoutYourInformationPage.isCancelBtnDisplayed(), "The cancel button did not appear.");
        soft.assertTrue(checkoutYourInformationPage.isCancelBtnEnabled(), "The cancel button is not active.");
        soft.assertEquals(checkoutYourInformationPage.getCancelBtnName(), "Cancel", "Incorrect continue button name");

        soft.assertTrue(checkoutYourInformationPage.isContinueBtnDisplayed(), "The continue button did not appear.");
        soft.assertTrue(checkoutYourInformationPage.isContinueBtnEnabled(), "The continue button is not active.");
        soft.assertEquals(checkoutYourInformationPage.getContinueBtnName(), "Continue", "Incorrect continue button name");

        soft.assertTrue(checkoutYourInformationPage.isFirstNameFieldDisplayed(), "The First Name Field did not appear.");
        soft.assertEquals(checkoutYourInformationPage.getFirstNameFieldPlaceholder(), "First Name", "Incorrect Placeholder on the First Name field");

        soft.assertTrue(checkoutYourInformationPage.isLastNameFieldDisplayed(), "The Last Name Field did not appear.");
        soft.assertEquals(checkoutYourInformationPage.getLastNameFieldPlaceholder(), "Last Name", "Incorrect Placeholder on the Last Name field");

        soft.assertTrue(checkoutYourInformationPage.isPostalCodeFieldDisplayed(), "The Postal Code Field did not appear.");
        soft.assertEquals(checkoutYourInformationPage.getPostalCodeFieldPlaceholder(), "Zip/Postal Code", "Incorrect Placeholder on the Postal Code field");

        soft.assertAll();
    }

    @Feature("Отправка формы с информацией о клиенте")
    @Story("Отправка формы с корректными данными")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка отправки формы с информацией о клиенте")
    public void checkSendingForm() {
        loginPage.open().login(withStandardPermission());
        productsPage.navigationPanel.clickCartLink();
        cartPage.clickCheckoutBtn();
        checkoutYourInformationPage.sendFilledForm(infoWithCorrectData());

        assertTrue(checkoutOverviewPage.isTitleDisplayed(), "The title did not appear.");
        assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview", "Incorrect checkout page name");
    }

    @Feature("Отправка формы с информацией о клиенте")
    @Story("Отправка формы с некорректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка отправки некорректной формы с информацией о клиенте", dataProvider = "IncorrectUserData")
    public void checkIncorrectSendingForm(UserData userData, String expectedErrorMsg) {
        loginPage.open().login(withStandardPermission());
        productsPage.navigationPanel.clickCartLink();
        cartPage.clickCheckoutBtn();
        checkoutYourInformationPage.sendFilledForm(userData);

        assertTrue(checkoutYourInformationPage.isErrorMsgDisplayed(), "The error message did not appear.");
        assertTrue(checkoutYourInformationPage.isCorrectBackgroundColorErrorMsg("226, 35, 26"), "The Background Color of Error Message is not correct.");
        assertEquals(checkoutYourInformationPage.getErrorMsg(), expectedErrorMsg, "Incorrect error message.");
    }

    @DataProvider(name = "IncorrectUserData")
    public Object[][] incorrectUserData() {
        return new Object[][]{
                {infoWithEmptyFields(), "Error: First Name is required"},
                {infoWithEmptyFirstName(), "Error: First Name is required"},
                {infoWithEmptyLastName(), "Error: Last Name is required"},
                {infoWithEmptyPostalCode(), "Error: Postal Code is required"}
        };
    }
}
