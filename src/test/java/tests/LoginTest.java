package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.ProductsPage.PRODUCTS_PAGE_URL;
import static user.UserFactory.*;

/**
 * Данный класс проверяет вход на сайт,
 * валидные и невалидные проверки.
 * Является дочерним от BaseTest.
 */
public class LoginTest extends BaseTest {
    /**
     * Проверка входа зарегистрированного пользователя.
     */
    @Test
    public void checkLogin() {
        loginPage.open();
        assertEquals(loginPage.getColorOfLoginButton(), "rgba(61, 220, 145, 1)",
                "The color of login button is incorrect");
        loginPage.login(withStandardPermission());

        assertTrue(productsPage.isPageTitleDisplayed(), "The products title didn't appear");
        assertEquals(productsPage.getPageTitle(), "Products",
                "Login failed with a valid username and password");
        assertEquals(productsPage.getUrl(), PRODUCTS_PAGE_URL, "Invalid URL after successful login");
    }

    /**
     * Проверка некорректного логина.
     */
    @Test(dataProvider = "incorrectData")
    public void checkIncorrectLogin(User user, String expectedErrorMsg) {
        loginPage.open().login(user);

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
