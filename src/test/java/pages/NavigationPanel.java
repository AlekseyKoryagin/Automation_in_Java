package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

import static pages.BasePage.DATA_TEST_CSS_PATTERN;

public class NavigationPanel {
    public static final String COMPANY_MAIN_PAGE_URL = PropertyReader.getProperty("automation_in_java.companyMainPageUrl");

    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By logoutLink = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("logout-sidebar-link"));
    private final By cartLink = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("shopping-cart-link"));
    private final By countThingsInCartBadge = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("shopping-cart-badge"));
    private final By aboutLink = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("about-sidebar-link"));

    WebDriver driver;

    public NavigationPanel(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажимаю на бургер меню")
    public NavigationPanel clickBurgerMenu() {
        driver.findElement(burgerMenu).click();
        return this;
    }

    @Step("Нажимаю на ссылку Logout")
    public void clickLinkLogout() {
        driver.findElement(logoutLink).click();
    }

    @Step("Нажимаю на иконку корзины")
    public void clickCartLink() {
        driver.findElement(cartLink).click();
    }

    @Step("Нажимаю на ссылку About")
    public void clickAboutLink() {
        driver.findElement(aboutLink).click();
    }

    @Step("Получаю количество товаров на иконке корзины")
    public String getCountThingsInCartBadge() {
        return driver.findElement(cartLink).getText();
    }

    @Step("Получаю количество товаров на иконке корзины")
    public int getCountThingsInCart() {
        return Integer.parseInt(driver.findElement(countThingsInCartBadge).getText());
    }

    @Step("Получаю цвет бейджа на иконке корзина")
    public String getColorOfCountThingsInCartBadge() {
        return driver.findElement(countThingsInCartBadge).getCssValue("background-color");
    }

    @Step("Проверка цвета бейджа на иконке корзина")
    public boolean isRightColorOfCountThingsInCartBadge(String subStrColor) {
        return getColorOfCountThingsInCartBadge().contains(subStrColor);
    }
}
