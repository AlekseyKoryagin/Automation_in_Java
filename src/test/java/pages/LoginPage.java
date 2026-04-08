package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    public static final String URL_LOGIN_PAGE = "https://www.saucedemo.com/";

    private final By fieldUsername = By.cssSelector("#user-name");
    private final By fieldPassword = By.cssSelector("[data-test='password']");
    private final By buttonLogin = By.xpath("//input[@data-test ='login-button']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(URL_LOGIN_PAGE);
    }

    public void login(String username, String password) {
        driver.findElement(fieldUsername).sendKeys(username);
        driver.findElement(fieldPassword).sendKeys(password);
        driver.findElement(buttonLogin).click();
    }

    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMsg() {
        return driver.findElement(errorMessage).getText();
    }

    public String getUrl() {
        return driver.getCurrentUrl();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(buttonLogin).isDisplayed();
    }
}
