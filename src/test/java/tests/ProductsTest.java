package tests;

import org.testng.annotations.Test;
import pages.LoginPage;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    @Test
    public void checkLoginOut() {
        loginPage.open();
        loginPage.login(LoginTest.USERNAME, LoginTest.PASSWORD);
        productsPage.clickBurgerMenu();
        productsPage.clickLinkLogout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "The login button did not appear");
        assertEquals(loginPage.getUrl(), LoginPage.URL_LOGIN_PAGE,
                "Invalid URL after successful login");
    }
}
