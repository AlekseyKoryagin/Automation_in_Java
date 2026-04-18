package pages;

import org.openqa.selenium.WebDriver;

import java.util.List;

public class BasePage {
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String DATA_TEST_CSS_PATTERN = "[data-test='%s']";
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";
    public static final List<String> GOODS_NAME = List.of("Sauce Labs Bike Light", "Sauce Labs Onesie", "Sauce Labs Backpack");

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
