package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

/**
 * Базовый тест-класс для тестов
 */
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;

    /**
     * Метод выполняется перед тестом.
     * Настраивает условия открытия браузера Chrome для тестов.
     * "--start-maximized" - окно браузера во весь экран.
     * "--headless" - запускает браузер в безголовом режиме.
     * "--guest" - войти как гость в браузере.
     * Неявное ожидание 10 сек.
     * Инициализируем классы LoginPage и ProductsPage/
     */
    @BeforeMethod
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
    }

    /**
     * Метод выполняется после теста.
     * Закрывает запушенный веб-драйвер.
     */
    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }
}
