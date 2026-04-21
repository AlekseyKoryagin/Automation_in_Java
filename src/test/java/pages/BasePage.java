package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String DATA_TEST_CSS_PATTERN = "[data-test='%s']";
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final List<String> GOODS_NAME = List.of("Sauce Labs Bike Light", "Sauce Labs Onesie", "Sauce Labs Backpack");

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
}
