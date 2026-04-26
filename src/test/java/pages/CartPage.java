package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    private final By goodsNames = By.xpath("//*[@data-test='inventory-item']//child::a");
    private final By checkoutBtn = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("checkout"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public List<String> getGoodsNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        List<WebElement> goodsTitle = driver.findElements(goodsNames);
        List<String> goodsNames = new ArrayList<>();
        for (WebElement goodTitle : goodsTitle) {
            goodsNames.add(goodTitle.getText());
        }
        return goodsNames;
    }

    public boolean isListEmpty(List<String> goodsInCart) {
        return goodsInCart.isEmpty();
    }

    public boolean isAddedGoodsInCart(List<String> goodsInCart, List<String> goodsNames) {
        for (String goodInCart : goodsInCart) {
            boolean found = false;
            for (String goodsName : goodsNames) {
                if (goodInCart.equals(goodsName)) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                return false;
            }
        }
        return true;
    }

    public void clickCheckoutBtn() {
        driver.findElement(checkoutBtn).click();
    }

    public void deleteGoodsFromCart(String goodsName) {
        driver.findElement(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).click();
    }

    public boolean isRemoveBtnDisplayed(String goodsName) {
        return driver.findElement(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).isDisplayed();
    }

    public boolean hasNotRemoveBtn(String goodsName) {
        return driver.findElements(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).isEmpty();
    }
}
