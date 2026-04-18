package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CartPage;
import pages.CompanyMainPage;
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
    CompanyMainPage companyMainPage;

    /**
     * Метод выполняется перед тестом.
     * Настраивает условия открытия браузера Chrome для тестов.
     * "--start-maximized" - окно браузера во весь экран.
     * "--headless" - запускает браузер в безголовом режиме.
     * "--guest" - войти как гость в браузере.
     * Неявное ожидание 5 сек.
     * Инициализируем классы LoginPage, ProductsPage, CartPage.
     */
    @BeforeMethod
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        companyMainPage = new CompanyMainPage(driver);
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
