package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    private final By usernameField = By.cssSelector("#user-name");
    private final By passwordField = By.cssSelector("[data-test='password']");
    private final By loginButton = By.xpath("//input[@data-test ='login-button']");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String username, String password) {
        fillUsernameField(username);
        fillPasswordField(password);
        clickLoginButton();
    }

    public void fillUsernameField(String username) {
        driver.findElement(usernameField).sendKeys(username);
    }

    public void fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
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
