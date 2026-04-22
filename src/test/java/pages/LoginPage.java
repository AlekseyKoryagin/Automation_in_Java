package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameField = By.cssSelector("#user-name");
    private final By passwordField = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("password"));
    private final By loginButton = By.xpath("//input[@data-test ='login-button']");
    private final By errorMessage = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    public void login(User user) {
        fillUsernameField(user.getUsername()).fillPasswordField(user.getPassword()).clickLoginButton();
    }

    public LoginPage fillUsernameField(String username) {
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    public String getErrorMsg() {
        return driver.findElement(errorMessage).getText();
    }

    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    public String getColorOfLoginButton() {
        return driver.findElement(loginButton).getCssValue("background-color");
    }
}
