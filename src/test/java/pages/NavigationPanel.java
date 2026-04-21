package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

public class NavigationPanel {
    public static final String COMPANY_MAIN_PAGE_URL = PropertyReader.getProperty("automation_in_java.companyMainPageUrl");

    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logoutLink = By.cssSelector(BasePage.DATA_TEST_CSS_PATTERN.formatted("logout-sidebar-link"));
    private final By cartLink = By.cssSelector(BasePage.DATA_TEST_CSS_PATTERN.formatted("shopping-cart-link"));
    private final By countThingsInCartBadge = By.cssSelector(BasePage.DATA_TEST_CSS_PATTERN.formatted("shopping-cart-badge"));
    private final By aboutLink = By.cssSelector(BasePage.DATA_TEST_CSS_PATTERN.formatted("about-sidebar-link"));

    WebDriver driver;

    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
    }

    public NavigationPanel clickBurgerMenu() {
        driver.findElement(burgerMenu).click();
        return this;
    }

    public void clickLinkLogout() {
        driver.findElement(logoutLink).click();
    }

    public void clickCartLink() {
        driver.findElement(cartLink).click();
    }

    public void clickAboutLink() {
        driver.findElement(aboutLink).click();
    }

    public String getCountThingsInCartBadge() {
        return driver.findElement(cartLink).getText();
    }

    public int getCountThingsInCart() {
        return Integer.parseInt(driver.findElement(countThingsInCartBadge).getText());
    }

    public String getColorOfCountThingsInCartBadge() {
        return driver.findElement(countThingsInCartBadge).getCssValue("background-color");
    }
}
