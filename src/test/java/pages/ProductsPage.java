package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertyReader;

import java.util.ArrayList;
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

    public String getPageTitle() {
        return driver.findElement(pageTitle).getText();
    }

    public boolean isPageTitleDisplayed() {
        return driver.findElement(pageTitle).isDisplayed();
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
     * @return возвращает число double.
     */
    public double getProductPrice(String productName) {
        return Double.parseDouble(driver.findElement(By.xpath(PRODUCT_PRICE.formatted(productName))).getText().replace("$", ""));
    }

    public List<Double> getProductPriceList(List<String> goodsNames) {
        List<Double> prices = new ArrayList<>();
        for (String name : goodsNames) {
            prices.add(getProductPrice(name));
        }
        return prices;
    }

    public double getTotalCostOfGoods(List<Double> prices) {
        double totalCost = 0.00;
        for (Double prise : prices) {
            totalCost += prise;
        }
        return totalCost;
    }
}
