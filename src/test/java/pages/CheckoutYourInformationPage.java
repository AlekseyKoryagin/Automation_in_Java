package pages;

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

    public boolean isCancelBtnDisplayed() {
        return driver.findElement(cancelBtn).isDisplayed();
    }

    public boolean isCancelBtnEnabled() {
        return driver.findElement(cancelBtn).isEnabled();
    }

    public boolean isContinueBtnDisplayed() {
        return driver.findElement(continueBtn).isDisplayed();
    }

    public boolean isContinueBtnEnabled() {
        return driver.findElement(continueBtn).isEnabled();
    }

    public String getCancelBtnName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(cancelBtn)).getText();
    }

    public String getContinueBtnName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(continueBtn)).getAttribute("value");
    }

    public boolean isLastNameFieldDisplayed() {
        return driver.findElement(lastNameField).isDisplayed();
    }

    public String getLastNameFieldPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(lastNameField)).getAttribute("placeholder");
    }

    public boolean isPostalCodeFieldDisplayed() {
        return driver.findElement(postalCodeField).isDisplayed();
    }

    public String getPostalCodeFieldPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(postalCodeField)).getAttribute("placeholder");
    }

    public boolean isFirstNameFieldDisplayed() {
        return driver.findElement(firstNameField).isDisplayed();
    }

    public String getFirstNameFieldPlaceholder() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameField)).getAttribute("placeholder");
    }

    public CheckoutYourInformationPage fillFirstNameField(String firstName) {
        driver.findElement(firstNameField).sendKeys(firstName);
        return this;
    }

    public CheckoutYourInformationPage fillLastNameField(String lastName) {
        driver.findElement(lastNameField).sendKeys(lastName);
        return this;
    }

    public CheckoutYourInformationPage fillPostalCodeField(String postalCode) {
        driver.findElement(postalCodeField).sendKeys(postalCode);
        return this;
    }

    public void clickContinueBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(continueBtn)).click();
    }

    public void sendFilledForm(UserData userData) {
        fillFirstNameField(userData.getFirstName()).fillLastNameField(userData.getLastName()).fillPostalCodeField(userData.getPostalCode()).clickContinueBtn();
    }

    public boolean isErrorMsgDisplayed() {
        return driver.findElement(errorMsg).isDisplayed();
    }

    public String getErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg)).getText();
    }

    public String getBackgroundColorErrorMsg() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsgBlock)).getCssValue("background-color");
    }

    public boolean isCorrectBackgroundColorErrorMsg(String subStr) {
        return getBackgroundColorErrorMsg().contains(subStr);
    }
}
