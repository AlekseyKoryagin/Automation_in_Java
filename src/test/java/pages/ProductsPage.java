package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertyReader;

import java.math.BigDecimal;
import java.util.List;

public class ProductsPage extends BasePage {
    public static final String PRODUCTS_PAGE_URL = PropertyReader.getProperty("automation_in_java.productsPageUrl");
    public static final String PRODUCT_PRICE = "//*[text()='%s']" +
            "//ancestor::div[@data-test='inventory-item']//child::div[@data-test='inventory-item-price']";

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

    public String getColorOfBorderButton(String goodsName) {
        return driver.findElement(By.xpath(ADD_TO_CART_AND_REMOVE_BUTTON_PATTERN.formatted(goodsName))).getCssValue("border");
    }

    /**
     * Проверка цвета бордюра кнопки Remove, в экранном и безголовом режиме.
     * @param goodsName   принимает название товара.
     * @param subStrColor принимает суб-строку, которую должен содержать полученный цвет.
     */
    public boolean isRightBorderColorBtn(String goodsName, String subStrColor) {
        return getColorOfBorderButton(goodsName).contains(subStrColor);
    }

    /**
     * Получает цену товара.
     * @param productName принимает название товара.
     * @return возвращает число BigDecimal.
     */
    public BigDecimal getProductPrice(String productName) {
        return new BigDecimal(driver.findElement(By.xpath(PRODUCT_PRICE.formatted(productName))).getText().replace("$", "").trim());
    }

    public List<BigDecimal> getProductPriceList(List<String> goodsNames) {
        return goodsNames.stream().map(this::getProductPrice).toList();
    }

    public BigDecimal getTotalCostOfGoods(List<BigDecimal> prices) {
        BigDecimal totalCost = BigDecimal.ZERO;
        for (BigDecimal price : prices) {
            totalCost = totalCost.add(price);
        }
        return totalCost;
    }
}
