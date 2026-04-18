package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductsPage extends BasePage {
    public static final String URL_PRODUCTS_PAGE = "https://www.saucedemo.com/inventory.html";
    public static final String ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN = "//*[text()='%s']" +
            "//ancestor::div[@data-test='inventory-item']//child::button";

    private final By pageTitle = By.cssSelector(DATA_TEST_CSS_PATTERN.formatted("title"));
    private final By addToCartButton = By.xpath("//*[text()='Add to cart']");

    public ProductsPage(WebDriver driver) {
        super(driver);
    }

    public void addFirstThingToCart() {
        driver.findElements(addToCartButton).getFirst().click();
    }

    public void addAllThingsToCart() {
        List<WebElement> buttons = driver.findElements(addToCartButton);
        for (WebElement button : buttons) {
            button.click();
        }
    }

    /**
     * Добавляет товар по названию в корзину.
     * @param goodsName принимает название товара.
     */
    public ProductsPage addGoogsToCart(String goodsName) {
        driver.findElement(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).click();
        return this;
    }

    /**
     * Добавляет товары в корзину из списка.
     * @param goodsNames принимает список товаров.
     */
    public ProductsPage addGoodsToCartFromList(List<String> goodsNames) {
        for (String goodsName : goodsNames) {
            addGoogsToCart(goodsName);
        }
        return this;
    }

    /**
     * Удаляет добавленные товары из корзины из списка.
     * @param goodsNames принимает список товаров.
     */
    public void deleteGoodsFromCartFromList(List<String> goodsNames) {
        for (String goodsName : goodsNames) {
            driver.findElement(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).click();
        }
    }

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean pageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
    }

    public String getColorOfBorderButton(String goodsName) {
        return driver.findElement(
                By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).getCssValue("border");
    }

    /**
     * Проверка цвета бордюра кнопки Remove, в экранном и безголовом режиме.
     * @param goodsName   принимает название товара.
     * @param subStrColor принимает суб-строку, которую должен содержать полученный цвет.
     */
    public boolean isRightBorderColorBtn(String goodsName, String subStrColor) {
        return getColorOfBorderButton(goodsName).contains(subStrColor);
    }
}
