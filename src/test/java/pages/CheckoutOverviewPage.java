package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.math.BigDecimal;

public class CheckoutOverviewPage extends BasePage {
    private final By finishBtn = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("finish"));
    private final By priceTotal = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("subtotal-label"));
    private final By tax = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("tax-label"));
    private final By total = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("total-label"));

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFinishBtnDisplayed() {
        return driver.findElement(finishBtn).isDisplayed();
    }

    public boolean isFinishBtnEnabled() {
        return driver.findElement(finishBtn).isEnabled();
    }

    public String getFinishBtnName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(finishBtn)).getText();
    }

    public void clickFinishBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    public BigDecimal getPriceTotal() {
        String[] price = wait.until(ExpectedConditions.visibilityOfElementLocated(priceTotal)).getText().split("\\$");
        return new BigDecimal(price[1]);
    }

    public boolean isSameCost(BigDecimal totalCost) {
        return compareDoubleNumbers(totalCost, getPriceTotal());
    }

    public BigDecimal getTax() {
        String[] price = wait.until(ExpectedConditions.visibilityOfElementLocated(tax)).getText().split("\\$");
        return new BigDecimal(price[1]);
    }

    public BigDecimal getTotal() {
        String[] price = wait.until(ExpectedConditions.visibilityOfElementLocated(total)).getText().split("\\$");
        return new BigDecimal(price[1]);
    }

    public boolean isTotalEqualCostAndTax() {
        return compareDoubleNumbers(getTotal(), (getPriceTotal().add(getTax())));
    }

    public boolean compareDoubleNumbers(BigDecimal firstNum, BigDecimal secondNum) {
        return firstNum.compareTo(secondNum) == 0;
    }
}
