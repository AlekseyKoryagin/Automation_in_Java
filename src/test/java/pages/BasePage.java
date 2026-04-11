package pages;

import org.openqa.selenium.WebDriver;

public class BasePage {
    public static final String BASE_URL = "https://www.saucedemo.com/";
    public static final String USERNAME = "standard_user";
    public static final String PASSWORD = "secret_sauce";

    WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }
}
