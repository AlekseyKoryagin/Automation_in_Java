package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {
    WebDriver driver;

    private final By title = By.cssSelector("[data-test='title']");
    private final By burgerMenu = By.id("react-burger-menu-btn");
    private final By linkLogout = By.cssSelector("[data-test='logout-sidebar-link']");

    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.findElement(title).getText();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public void clickBurgerMenu() {
        driver.findElement(burgerMenu).click();
    }

    public void clickLinkLogout() {
        driver.findElement(linkLogout).click();
    }
}
