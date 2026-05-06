package tests;

import io.qameta.allure.*;
import org.testng.annotations.*;
import user.User;

import static enums.ElementColors.GREEN;
import static enums.PageTitles.PRODUCTS;
import static org.testng.Assert.*;
import static pages.ProductsPage.PRODUCTS_PAGE_URL;
import static user.UserFactory.*;

/**
 * Данный класс проверяет вход на сайт,
 * валидные и невалидные проверки.
 * Является дочерним от BaseTest.
 */
@Epic("Проверка страницы Login")
@Owner("Aleksey Ivanov test@test.ru")
public class LoginTest extends BaseTest {
    /**
     * Проверка входа зарегистрированного пользователя.
     */
    @Feature("Проверка авторизации")
    @Story("С корректными данными")
    @Severity(SeverityLevel.BLOCKER)
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка авторизации")
    public void checkLogin() {
        loginPage.open();
        assertTrue(loginPage.isRightColorBtn(GREEN.getElementColor()), "The color of login button is incorrect");
        loginPage.login(withStandardPermission());

        assertTrue(productsPage.isTitleDisplayed(), "The products title didn't appear");
        assertEquals(productsPage.getTitle(), PRODUCTS.getPageTitle(), "Login failed with a valid username and password");
        assertEquals(productsPage.getUrl(), PRODUCTS_PAGE_URL, "Invalid URL after successful login");
    }

    /**
     * Проверка некорректного логина.
     */
    @Feature("Проверка авторизации")
    @Story("С некорректными данными")
    @Severity(SeverityLevel.CRITICAL)
    @TmsLink("Automation_in_Java")
    @Issue("yandex.ru")
    @Test(description = "Проверка некорректной авторизации", dataProvider = "incorrectData")
    public void checkIncorrectLogin(User user, String expectedErrorMsg) {
        loginPage
                .open()
                .login(user);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(), expectedErrorMsg, "The error message displayed is incorrect.");
    }

    @DataProvider(name = "incorrectData")
    public Object[][] loginData() {
        return new Object[][]{
                {withWrongPassword(), "Epic sadface: Username and password do not match any user in this service"},
                {withWrongUsername(), "Epic sadface: Username and password do not match any user in this service"},
                {withLockedOutUser(), "Epic sadface: Sorry, this user has been locked out."},
                {withEmptyFields(), "Epic sadface: Username is required"},
                {withEmptyUsernameField(), "Epic sadface: Username is required"},
                {withEmptyPasswordField(), "Epic sadface: Password is required"},
                {withUpperCaseUsername(), "Epic sadface: Username and password do not match any user in this service"},
        };
    }
}
