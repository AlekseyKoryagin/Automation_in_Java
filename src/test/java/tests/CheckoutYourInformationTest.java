package tests;

import org.testng.annotations.*;
import user.UserData;

import static org.testng.Assert.*;
import static user.UserFactory.*;

public class CheckoutYourInformationTest extends BaseTest {
    @Test
    public void checkGoToCheckout() {
        loginPage.open().login(withStandardPermission());
        productsPage.navigationPanel.clickCartLink();
        cartPage.clickCheckoutBtn();

        assertTrue(checkoutYourInformationPage.isTitleDisplayed(), "The title did not appear.");
        assertEquals(checkoutYourInformationPage.getTitle(), "Checkout: Your Information", "Incorrect checkout page name");

        assertTrue(checkoutYourInformationPage.isCancelBtnDisplayed(), "The cancel button did not appear.");
        assertTrue(checkoutYourInformationPage.isCancelBtnEnabled(), "The cancel button is not active.");
        assertEquals(checkoutYourInformationPage.getCancelBtnName(), "Cancel", "Incorrect continue button name");

        assertTrue(checkoutYourInformationPage.isContinueBtnDisplayed(), "The continue button did not appear.");
        assertTrue(checkoutYourInformationPage.isContinueBtnEnabled(), "The continue button is not active.");
        assertEquals(checkoutYourInformationPage.getContinueBtnName(), "Continue", "Incorrect continue button name");

        assertTrue(checkoutYourInformationPage.isFirstNameFieldDisplayed(), "The First Name Field did not appear.");
        assertEquals(checkoutYourInformationPage.getFirstNameFieldPlaceholder(), "First Name", "Incorrect Placeholder on the First Name field");

        assertTrue(checkoutYourInformationPage.isLastNameFieldDisplayed(), "The Last Name Field did not appear.");
        assertEquals(checkoutYourInformationPage.getLastNameFieldPlaceholder(), "Last Name", "Incorrect Placeholder on the Last Name field");

        assertTrue(checkoutYourInformationPage.isPostalCodeFieldDisplayed(), "The Postal Code Field did not appear.");
        assertEquals(checkoutYourInformationPage.getPostalCodeFieldPlaceholder(), "Zip/Postal Code", "Incorrect Placeholder on the Postal Code field");
    }

    @Test
    public void checkSendingForm() {
        loginPage.open().login(withStandardPermission());
        productsPage.navigationPanel.clickCartLink();
        cartPage.clickCheckoutBtn();
        checkoutYourInformationPage.sendFilledForm(infoWithCorrectData());

        assertTrue(checkoutOverviewPage.isTitleDisplayed(), "The title did not appear.");
        assertEquals(checkoutOverviewPage.getTitle(), "Checkout: Overview", "Incorrect checkout page name");
    }

    @Test(dataProvider = "IncorrectUserData")
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
