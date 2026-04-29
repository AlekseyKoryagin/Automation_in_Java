package pages;

import io.qameta.allure.Step;
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

    @Step("Проверка. Кнопка Finish видна")
    public boolean isFinishBtnDisplayed() {
        return driver.findElement(finishBtn).isDisplayed();
    }

    @Step("Проверка. Кнопка Finish активна")
    public boolean isFinishBtnEnabled() {
        return driver.findElement(finishBtn).isEnabled();
    }

    @Step("Получение название кнопки Finish")
    public String getFinishBtnName() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(finishBtn)).getText();
    }

    @Step("Нажимаю на кнопку Finish")
    public void clickFinishBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(finishBtn)).click();
    }

    @Step("Получаю сумму Price Total")
    public BigDecimal getPriceTotal() {
        String[] price = wait.until(ExpectedConditions.visibilityOfElementLocated(priceTotal)).getText().split("\\$");
        return new BigDecimal(price[1]);
    }

    @Step("Проверка. Сумма добавленных товаров равна Price Total")
    public boolean isSameCost(BigDecimal totalCost) {
        return compareDoubleNumbers(totalCost, getPriceTotal());
    }

    @Step("Получаю сумму налогов на товары")
    public BigDecimal getTax() {
        String[] price = wait.until(ExpectedConditions.visibilityOfElementLocated(tax)).getText().split("\\$");
        return new BigDecimal(price[1]);
    }

    @Step("Получаю общую сумму товаров с налогами Total")
    public BigDecimal getTotal() {
        String[] price = wait.until(ExpectedConditions.visibilityOfElementLocated(total)).getText().split("\\$");
        return new BigDecimal(price[1]);
    }

    @Step("Проверка. Сумма Total равна сумме товаров и налога")
    public boolean isTotalEqualCostAndTax() {
        return compareDoubleNumbers(getTotal(), (getPriceTotal().add(getTax())));
    }

    @Step("Проверка. Сравниваю две суммы товаров")
    public boolean compareDoubleNumbers(BigDecimal firstNum, BigDecimal secondNum) {
        return firstNum.compareTo(secondNum) == 0;
    }
}
