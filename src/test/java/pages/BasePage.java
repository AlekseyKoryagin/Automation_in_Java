package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.PropertyReader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    public static final String BASE_URL = PropertyReader.getProperty("automation_in_java.baseUrl");
    public static final String DATA_TEST_CSS_PATTERN = "[data-test='%s']";
    public static final List<String> GOODS_NAME = List.of("Sauce Labs Bike Light", "Sauce Labs Onesie", "Sauce Labs Backpack");

    WebDriver driver;
    WebDriverWait wait;
    public NavigationPanel navigationPanel;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.navigationPanel = new NavigationPanel(driver);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
