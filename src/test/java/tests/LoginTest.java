package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Данный класс проверяет вход на сайт,
 * валидные и невалидные проверки.
 * Является дочерним от BaseTest.
 */
public class LoginTest extends BaseTest {
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";

    private final String wrongUsername = "user";
    private final String userIsBlocked = "locked_out_user";
    private final String emptyUsername = "";
    private final String wrongPassword = "pass";
    private final String emptyPassword = "";

    /**
     * Проверка входа зарегистрированного пользователя.
     */
    @Test
    public void successfulLogin() {
        loginPage.open();
        loginPage.login(USERNAME, PASSWORD);

        assertEquals(productsPage.getTitle(), "Products",
                "Login failed with a valid username and password");
        assertEquals(productsPage.getUrl(),
                "https://www.saucedemo.com/inventory.html",
                "Invalid URL after successful login");
    }

    /**
     * Проверка входа зарегистрированного пользователя
     * с верным логином и неверным паролем.
     */
    @Test
    public void failedLoginInvalidPassword() {
        loginPage.open();
        loginPage.login(USERNAME, wrongPassword);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(),
                "Epic sadface: Username and password do not match any user in this service",
                "The error message displayed is incorrect.");
    }

    /**
     * Проверка входа зарегистрированного пользователя
     * с неверным логином и верным паролем.
     */
    @Test
    public void failedLoginInvalidUsername() {
        loginPage.open();
        loginPage.login(wrongUsername, PASSWORD);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(),
                "Epic sadface: Username and password do not match any user in this service",
                "The error message displayed is incorrect.");
    }

    /**
     * Проверка входа заблокированного пользователя.
     */
    @Test
    public void failedLoginUserIsBlocked() {
        loginPage.open();
        loginPage.login(userIsBlocked, PASSWORD);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(),
                "Epic sadface: Sorry, this user has been locked out.",
                "The error message displayed is incorrect.");
    }

    /**
     * Проверка входа с пустыми полями Username и Password.
     */
    @Test
    public void loginWithEmptyFields() {
        loginPage.open();
        loginPage.login(emptyUsername, emptyPassword);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(),
                "Epic sadface: Username is required",
                "The error message displayed is incorrect.");
    }

    /**
     * Проверка входа с пустым полем Username и верным паролем.
     */
    @Test
    public void loginWithEmptyUsernameField() {
        loginPage.open();
        loginPage.login(emptyUsername, PASSWORD);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(),
                "Epic sadface: Username is required",
                "The error message displayed is incorrect.");
    }

    /**
     * Проверка входа с пустым полем Password и верным Username.
     */
    @Test
    public void loginWithPasswordField() {
        loginPage.open();
        loginPage.login(USERNAME, emptyPassword);

        assertTrue(loginPage.isErrorMsgDisplayed(), "The error message did not appear");
        assertEquals(loginPage.getErrorMsg(),
                "Epic sadface: Password is required",
                "The error message displayed is incorrect.");
    }
}
