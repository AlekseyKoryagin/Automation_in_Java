package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

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
        return driver.findElement(finishBtn).getText();
    }

    public void clickFinishBtn() {
        driver.findElement(finishBtn).click();
    }

    public double getPriceTotal() {
        String[] prise = (driver.findElement(priceTotal).getText()).split("\\$");
        return Double.parseDouble(prise[1]);
    }

    public boolean isSameCost(double totalCost) {
        return totalCost == getPriceTotal();
    }

    public double getTax() {
        String[] prise = (driver.findElement(tax).getText()).split("\\$");
        return Double.parseDouble(prise[1]);
    }

    public double getTotal() {
        String[] prise = (driver.findElement(total).getText()).split("\\$");
        return Double.parseDouble(prise[1]);
    }

    public boolean isTotalEqualCostAndTax() {
        return getTotal() == getPriceTotal() + getTax();
    }
}
