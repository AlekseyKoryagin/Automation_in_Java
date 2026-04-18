package tests;

import org.testng.annotations.Test;
import pages.BasePage;
import pages.CompanyMainPage;

import static org.testng.Assert.assertEquals;

public class CompanyTest extends BaseTest{
    @Test
    public void checkGoToAboutCompanyPage() {
        loginPage.open().login(BasePage.USERNAME, BasePage.PASSWORD);
        productsPage.navigationPanel.clickBurgerMenu().clickAboutLink();

        assertEquals(companyMainPage.getUrl(), CompanyMainPage.COMPANY_MAIN_PAGE_URL, "The wrong site page has opened.");
    }
}
