package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import user.User;

public class LoginPage extends BasePage {
    @FindBy(css = "#user-name")
    private WebElement usernameField;

    @FindBy(css = "[data-test='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//input[@data-test ='login-button']")
    private WebElement loginButton;

    @FindBy(css = "[data-test='error']")
    private WebElement errorMessage;

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
        usernameField.sendKeys(username);
        return this;
    }

    public LoginPage fillPasswordField(String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public void clickLoginButton() {
        loginButton.click();
    }

    public boolean isErrorMsgDisplayed() {
        return errorMessage.isDisplayed();
    }

    public String getErrorMsg() {
        return errorMessage.getText();
    }

    public boolean isLoginButtonDisplayed() {
        return loginButton.isDisplayed();
    }

    public String getColorOfLoginButton() {
        return loginButton.getCssValue("background-color");
    }
}
