package tests;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

/**
 * Данный класс проверяет вход на сайт "https://www.saucedemo.com/",
 * валидные и невалидные проверки.
 * Является дочерним от BaseTest.
 */
public class LoginTest extends BaseTest {

    public final String URL_WEBSITE_SAUCEDEMO = "https://www.saucedemo.com/";
    public final String USER_NAME = "standard_user";
    public final String USER_PASSWORD = "secret_sauce";
    public final String USER_IS_BLOCKED = "locked_out_user";
    public final String FIELD_USERNAME_CSS_ID = "#user-name";
    public final String FIELD_PASSWORD_CSS = "[data-test='password']";
    public final String BUTTON_LOGIN_XPATH = "//input[@data-test ='login-button']";
    public final String TITLE_PRODUCTS_CSS = "[data-test='title']";
    public final String ERROR_MESSAGE_CSS = "[data-test='error']";

    /**
     * Проверка входа зарегистрированного пользователя.
     */
    @Test
    public void successfulLogin() {
        driver.get(URL_WEBSITE_SAUCEDEMO);
        driver.findElement(By.cssSelector(FIELD_USERNAME_CSS_ID))
                .sendKeys(USER_NAME);
        driver.findElement(By.cssSelector(FIELD_PASSWORD_CSS))
                .sendKeys(USER_PASSWORD);
        driver.findElement(By.xpath(BUTTON_LOGIN_XPATH))
                .click();

        assertEquals(driver.findElement(By.cssSelector(TITLE_PRODUCTS_CSS)).getText(),
                "Products",
                "Login failed with a valid username and password");
        assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html",
                "Invalid URL after successful login");
    }

    /**
     *  Проверка входа зарегистрированного пользователя
     *  с верным логином и неверным паролем.
     */
    @Test
    public void failedLoginInvalidPassword() {
        driver.get(URL_WEBSITE_SAUCEDEMO);
        driver.findElement(By.cssSelector(FIELD_USERNAME_CSS_ID))
                .sendKeys(USER_NAME);
        driver.findElement(By.cssSelector(FIELD_PASSWORD_CSS))
                .sendKeys("pass");
        driver.findElement(By.xpath(BUTTON_LOGIN_XPATH)).click();

        assertTrue(driver.findElement(By.cssSelector(ERROR_MESSAGE_CSS)).isDisplayed(),
                "The error message did not appear");
        assertEquals(driver.findElement(By.cssSelector(ERROR_MESSAGE_CSS)).getText(),
                "Epic sadface: Username and password do not match any user in this service",
                "The error message displayed is incorrect.");
    }

    /**
     * Проверка входа зарегистрированного пользователя
     * с неверным логином и верным паролем.
     */
    @Test
    public void failedLoginInvalidUsername() {
        driver.get(URL_WEBSITE_SAUCEDEMO);
        driver.findElement(By.cssSelector(FIELD_USERNAME_CSS_ID))
                .sendKeys("user");
        driver.findElement(By.cssSelector(FIELD_PASSWORD_CSS))
                .sendKeys(USER_PASSWORD);
        driver.findElement(By.xpath(BUTTON_LOGIN_XPATH)).click();

        assertTrue(driver.findElement(By.cssSelector(ERROR_MESSAGE_CSS)).isDisplayed(),
                "The error message did not appear");
        assertEquals(driver.findElement(By.cssSelector(ERROR_MESSAGE_CSS)).getText(),
                "Epic sadface: Username and password do not match any user in this service",
                "The error message displayed is incorrect.");
    }

    /**
     *  Проверка входа заблокированного пользователя.
     */
    @Test
    public void failedLoginUserIsBlocked() {
        driver.get(URL_WEBSITE_SAUCEDEMO);
        driver.findElement(By.cssSelector(FIELD_USERNAME_CSS_ID))
                .sendKeys(USER_IS_BLOCKED);
        driver.findElement(By.cssSelector(FIELD_PASSWORD_CSS))
                .sendKeys(USER_PASSWORD);
        driver.findElement(By.xpath(BUTTON_LOGIN_XPATH)).click();

        assertTrue(driver.findElement(By.cssSelector(ERROR_MESSAGE_CSS)).isDisplayed(),
                "The error message did not appear");
        assertEquals(driver.findElement(By.cssSelector(ERROR_MESSAGE_CSS)).getText(),
                "Epic sadface: Sorry, this user has been locked out.",
                "The error message displayed is incorrect.");
    }

}
