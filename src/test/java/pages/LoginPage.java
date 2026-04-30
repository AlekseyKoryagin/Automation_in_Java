package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.User;

public class LoginPage extends BasePage {
    private final By usernameField = By.cssSelector("#user-name");
    private final By passwordField = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("password"));
    private final By loginButton = By.xpath("//input[@data-test ='login-button']");
    private final By errorMessage = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("error"));

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открываю страницу https://www.saucedemo.com/")
    public LoginPage open() {
        driver.get(BASE_URL);
        return this;
    }

    @Step("Авторизация пользователя: {user}")
    public void login(User user) {
        fillUsernameField(user.getUsername()).fillPasswordField(user.getPassword()).clickLoginButton();
    }

    @Step("Заполняю поле Username: {username}")
    public LoginPage fillUsernameField(String username) {
        driver.findElement(usernameField).sendKeys(username);
        return this;
    }

    @Step("Заполняю поле Password: {password}")
    public LoginPage fillPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    @Step("Нажимаю кнопку Login")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }

    @Step("Проверка. Сообщение ошибки появилось")
    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    @Step("Получаю текст сообщения ошибки")
    public String getErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage)).getText();
    }

    @Step("Проверка. Кнопка Login видна")
    public boolean isLoginButtonDisplayed() {
        return driver.findElement(loginButton).isDisplayed();
    }

    @Step("Получаю цвет кнопки")
    public String getColorOfLoginButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).getCssValue("background-color");
    }

    @Step("Проверка цвета кнопки")
    public boolean isRightColorBtn(String subStrColor) {
        return getColorOfLoginButton().contains(subStrColor);
    }
}
