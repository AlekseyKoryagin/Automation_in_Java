package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.UserData;

public class CheckoutYourInformationPage extends BasePage {
    private final By cancelBtn = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("cancel"));
    private final By continueBtn = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("continue"));
    private final By firstNameField = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("firstName"));
    private final By lastNameField = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("lastName"));
    private final By postalCodeField = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("postalCode"));
    private final By errorMsg = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("error"));
    private final By errorMsgBlock = By.cssSelector("[class^='error-message-container']");

    public CheckoutYourInformationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверка. Кнопка Cancel видна")
    public boolean isCancelBtnDisplayed() {
        return driver.findElement(cancelBtn).isDisplayed();
    }

    @Step("Проверка. Кнопка Cancel активна")
    public boolean isCancelBtnEnabled() {
        return driver.findElement(cancelBtn).isEnabled();
    }
    @Step("Проверка. Кнопка Continue видна")
    public boolean isContinueBtnDisplayed() {
        return driver.findElement(continueBtn).isDisplayed();
    }

    @Step("Проверка. Кнопка Continue активна")
    public boolean isContinueBtnEnabled() {
        return driver.findElement(continueBtn).isEnabled();
    }

    @Step("Получаю название кнопки Cancel")
    public String getCancelBtnName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).getText();
    }

    @Step("Получаю название кнопки Continue")
    public String getContinueBtnName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn)).getAttribute("value");
    }

    @Step("Проверка. Поле Last Name видно")
    public boolean isLastNameFieldDisplayed() {
        return driver.findElement(lastNameField).isDisplayed();
    }

    @Step("Получаю Placeholder поля Last Name")
    public String getLastNameFieldPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).getAttribute("placeholder");
    }

    @Step("Проверка. Поле Postal Code видно")
    public boolean isPostalCodeFieldDisplayed() {
        return driver.findElement(postalCodeField).isDisplayed();
    }

    @Step("Получаю Placeholder поля Postal Code")
    public String getPostalCodeFieldPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).getAttribute("placeholder");
    }

    @Step("Проверка. Поле First Name видно")
    public boolean isFirstNameFieldDisplayed() {
        return driver.findElement(firstNameField).isDisplayed();
    }

    @Step("Получаю Placeholder поля First Name")
    public String getFirstNameFieldPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).getAttribute("placeholder");
    }

    @Step("Заполняю поле First Name: {firstName}")
    public CheckoutYourInformationPage fillFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    @Step("Заполняю поле Last Name: {lastName}")
    public CheckoutYourInformationPage fillLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    @Step("Заполняю поле Postal Code: {postalCode}")
    public CheckoutYourInformationPage fillPostalCodeField(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
        return this;
    }

    @Step("Нажимаю кнопку Continue")
    public void clickContinueBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    @Step("Отправляю заполненную форму: {userData}")
    public void sendFilledForm(UserData userData) {
        fillFirstNameField(userData.getFirstName()).fillLastNameField(userData.getLastName()).fillPostalCodeField(userData.getPostalCode()).clickContinueBtn();
    }

    @Step("Проверка. Сообщение ошибки появилось")
    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    @Step("Получаю текст сообщения ошибки")
    public String getErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }

    @Step("Получаю background сообщения ошибки")
    public String getBackgroundColorErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgBlock)).getCssValue("background-color");
    }

    @Step("Проверка. Цвет background корректный")
    public boolean isCorrectBackgroundColorErrorMsg(String subStr) {
        return getBackgroundColorErrorMsg().contains(subStr);
    }
}
