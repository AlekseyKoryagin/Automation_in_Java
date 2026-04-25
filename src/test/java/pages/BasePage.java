package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public static final String BASE_URL = PropertyReader.getProperty("automation_in_java.baseUrl");
    public static final String DATA_TEST_CSS_PATTERN = "[data-test='%s']";
    public static final String ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN = "//*[text()='%s']//ancestor::div[@data-test='inventory-item']//child::button";
    public static final List<String> GOODS_NAME = List.of("Sauce Labs Bike Light", "Sauce Labs Onesie", "Sauce Labs Backpack");
    public final By pageTitle = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("title"));

    WebDriver driver;
    WebDriverWait wait;
    public NavigationPanel navigationPanel;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.navigationPanel = new NavigationPanel(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }
}
