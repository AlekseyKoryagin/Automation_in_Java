package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class CartPage extends BasePage {
    private final By goodsNames = By.xpath("//*[@data-test='inventory-item']//child::a");
    private final By checkoutBtn = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("checkout"));

    public CartPage(WebDriver driver) {
        super(driver);
    }

    @Step("Получение названий добавленных товаров в корзине")
    public List<String> getGoodsNames() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(checkoutBtn));
        List<WebElement> goodsTitle = driver.findElements(goodsNames);
        return goodsTitle.stream().map(WebElement::getText).toList();
    }

    @Step("Проверка списка товаров из корзины на пустой список")
    public boolean isListEmpty(List<String> goodsInCart) {
        return goodsInCart.isEmpty();
    }

    @Step("Проверка. Добавленные товары на странице Товары находятся в корзине")
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

    @Step("Нажимаю кнопку Checkout")
    public void clickCheckoutBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutBtn)).click();
    }

    @Step("Удаление товара {goodsName}")
    public void deleteGoodsFromCart(String goodsName) {
        driver.findElement(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).click();
    }

    @Step("Проверяю видимость кнопки у товара {goodsName}")
    public boolean isRemoveBtnDisplayed(String goodsName) {
        return driver.findElement(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).isDisplayed();
    }

    @Step("Проверяю что товар {goodsName} удалён")
    public boolean hasNotRemoveBtn(String goodsName) {
        return driver.findElements(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).isEmpty();
    }
}
