package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    public static final String URL_PRODUCTS_PAGE = "https://www.saucedemo.com/inventory.html";

    private final By pageTitle = By.cssSelector("[data-test='title']");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By linkLogout = By.cssSelector("[data-test='logout-sidebar-link']");
    private final By addToCartButton = By.xpath("//*[text()='Add to cart']");
    private final By cartLink = By.cssSelector("[data-test='shopping-cart-link']");
    private final By countThingsInCartBadge = By.cssSelector("[data-test='shopping-cart-badge']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean pageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public void clickBurgerMenu() {
        driver.findElement(burgerMenu).click();
    }

    public void clickLinkLogout() {
        driver.findElement(linkLogout).click();
    }

    public void addFirstThingToCart() {
        driver.findElements(addToCartButton).getFirst().click();
    }

    public String getCountThingsInCart() {
        return driver.findElement(countThingsInCartBadge).getText();
    }

    public void addAllThingsToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButton);
        for (WebElement button : buttons) {
            button.click();
        }
    }

    public String getColorOfCountThingsInCartBadge() {
        return driver.findElement(countThingsInCartBadge).getCssValue("background-color");
    }

    public void clickCartLink() {
        driver.findElement(cartLink).click();
    }
}
