package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;
import static pages.BasePage.BASE_URL;
import static pages.NavigationPanel.COMPANY_MAIN_PAGE_URL;
import static user.UserFactory.withStandardPermission;

public class NavigationPanelTest extends BaseTest {
    @Test
    public void checkLoginOut() {
        loginPage.open().login(withStandardPermission());
        assertTrue(productsPage.isPageTitleDisplayed(), "The products title didn't appear");
        productsPage.navigationPanel.clickBurgerMenu().clickLinkLogout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "The login button did not appear");
        assertEquals(loginPage.getUrl(), BASE_URL, "Invalid URL after successful login");
    }

    @Test
    public void checkGoToAboutCompanyPage() {
        loginPage.open().login(withStandardPermission());
        productsPage.navigationPanel.clickBurgerMenu().clickAboutLink();

        assertEquals(productsPage.getUrl(), COMPANY_MAIN_PAGE_URL, "The wrong site page has opened.");
    }

    @Test
    public void checkGoToCart() {
        loginPage.open().login(withStandardPermission());
        productsPage.navigationPanel.clickCartLink();

        assertTrue(cartPage.isTitleDisplayed(), "The cart title didn't appear");
        assertEquals(cartPage.getTitle(), "Your Cart", "Incorrect page Title");
    }
}
