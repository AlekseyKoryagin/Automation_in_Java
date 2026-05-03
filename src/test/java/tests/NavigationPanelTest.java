package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import static enums.PageTitles.CART;
import static org.testng.Assert.*;
import static pages.BasePage.BASE_URL;
import static pages.NavigationPanel.COMPANY_MAIN_PAGE_URL;
import static user.UserFactory.withStandardPermission;

@Epic("Проверка навигационной панели")
public class NavigationPanelTest extends BaseTest {
    @Story("Проверка выхода из учётной записи")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка выхода из учётной записи")
    public void checkLoginOut() {
        loginPage
                .open()
                .login(withStandardPermission());
        assertTrue(productsPage.isTitleDisplayed(), "The products title didn't appear");
        productsPage.navigationPanel
                .clickBurgerMenu()
                .clickLinkLogout();

        assertTrue(loginPage.isLoginButtonDisplayed(), "The login button did not appear");
        assertEquals(loginPage.getUrl(), BASE_URL, "Invalid URL after successful login");
    }

    @Story("Проверка перехода на страницу о компании")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Issue("yandex.ru")
    @Test(description = "Проверка перехода на страницу о компании")
    public void checkGoToAboutCompanyPage() {
        loginPage
                .open()
                .login(withStandardPermission());
        productsPage.navigationPanel
                .clickBurgerMenu()
                .clickAboutLink();

        assertEquals(productsPage.getUrl(), COMPANY_MAIN_PAGE_URL, "The wrong site page has opened.");
    }

    @Story("Проверка перехода в корзину")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Aleksey Ivanov test@test.ru")
    @TmsLink("Automation_in_Java")
    @Test(description = "Проверка перехода в корзину")
    public void checkGoToCart() {
        loginPage
                .open()
                .login(withStandardPermission());
        productsPage.navigationPanel.clickCartLink();

        assertTrue(cartPage.isTitleDisplayed(), "The cart title didn't appear");
        assertEquals(cartPage.getTitle(), CART.getPageTitle(), "Incorrect page Title");
    }
}
