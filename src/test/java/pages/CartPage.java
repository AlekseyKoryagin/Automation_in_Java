package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class CartPage extends BasePage {
    public static final String REMOVE_BUTTON_PATTERN = "//*[text()='%s']" +
            "//ancestor::div[@data-test='inventory-item']//child::button";

    private final By pageTitle = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("title"));
    private final By goodsNames = By.xpath("//*[@data-test='inventory-item']//child::a");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public List<String> getGoodsNames() {
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

    public void deleteGoodsFromCart(String goodsName) {
        driver.findElement(By.xpath(REMOVE_BUTTON_PATTERN.formatted(goodsName))).click();
    }

    public boolean isRemoveBtnDisplayed(String goodsName) {
        return driver.findElement(By.xpath(REMOVE_BUTTON_PATTERN.formatted(goodsName))).isDisplayed();
    }

    public boolean hasNotRemoveBtn(String goodsName) {
        return driver.findElements(By.xpath(REMOVE_BUTTON_PATTERN.formatted(goodsName))).isEmpty();
    }
}
