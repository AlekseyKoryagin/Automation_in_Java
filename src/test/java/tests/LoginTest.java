package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.ProductsPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
        loginPage.login(BasePage.USERNAME, BasePage.PASSWORD);

        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        assertEquals(productsPage.getPageTitle(), "Products",
                "Login failed with a valid username and password");
        assertEquals(productsPage.getUrl(), ProductsPage.URL_PRODUCTS_PAGE,
                "Invalid URL after successful login");
    }

    /**
     * Проверка некорректного логина.
     */
    @Test(dataProvider = "incorrectData")
    public void checkIncorrectLogin(String username, String password, String expectedErrorMsg) {
        loginPage.open().login(username, password);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(), expectedErrorMsg, "The error message displayed is incorrect.");
    }

    @DataProvider(name = "incorrectData")
    public Object[][] loginData() {
        return new Object[][]{
                {"standard_user", "pass", "Epic sadface: Username and password do not match any user in this service"},
                {"user", "secret_sauce", "Epic sadface: Username and password do not match any user in this service"},
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"", "", "Epic sadface: Username is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"Standard_user", "Standard_user", "Epic sadface: Username and password do not match any user in this service"},
        };
    }
}
