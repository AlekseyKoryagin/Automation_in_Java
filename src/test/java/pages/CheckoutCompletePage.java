package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CheckoutCompletePage extends BasePage {
    private final By successMessage = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("complete-header"));
    private final By backHomeBtn = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("back-to-products"));

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isSuccessMessage() {
        return driver.findElement(successMessage).isDisplayed();
    }

    public String getSuccessMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage)).getText();
    }

    public boolean isBackHomeBtnDisplayed() {
        return driver.findElement(backHomeBtn).isDisplayed();
    }
}
