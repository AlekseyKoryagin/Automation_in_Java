package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.NavigationPanel;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class NavigationPanelTest extends BaseTest {
    @Test
    public void checkLoginOut() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        assertTrue(productsPage.pageTitleDisplayed(), "The products title didn't appear");
        productsPage.navigationPanel.clickBurgerMenu().clickLinkLogout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "The login button did not appear");
        assertEquals(loginPage.getUrl(), BasePage.BASE_URL, "Invalid URL after successful login");
    }

    @Test
    public void checkGoToAboutCompanyPage() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.navigationPanel.clickBurgerMenu().clickAboutLink();

        assertEquals(productsPage.getUrl(), NavigationPanel.COMPANY_MAIN_PAGE_URL, "The wrong site page has opened.");
    }
}
