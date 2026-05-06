package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import io.qameta.allure.testng.AllureTestNg;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestContext;
import org.testng.annotations.*;
import org.testng.asserts.SoftAssert;
import pages.*;
import utils.TestListener;

import java.time.Duration;

/**
 * Базовый тест-класс для тестов
 */
@Listeners({AllureTestNg.class, TestListener.class})
public class BaseTest {
    WebDriver driver;
    LoginPage loginPage;
    ProductsPage productsPage;
    CartPage cartPage;
    CheckoutYourInformationPage checkoutYourInformationPage;
    CheckoutOverviewPage checkoutOverviewPage;
    CheckoutCompletePage checkoutCompletePage;
    SoftAssert soft;

    /**
     * Метод выполняется перед тестом.
     * Настраивает условия открытия браузера Chrome для тестов.
     * "--start-maximized" - окно браузера во весь экран.
     * "--headless" - запускает браузер в безголовом режиме.
     * "--guest" - войти как гость в браузере.
     * Неявное ожидание 5 сек.
     */
    @Step("Запуск браузера {browser}")
    @BeforeMethod
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browser, ITestContext context) {
        if (browser.equalsIgnoreCase("chrome")) {
            startChrome();
        } else if (browser.equalsIgnoreCase("firefox")) {
            startFirefox();
        } else if (browser.equalsIgnoreCase("edge")) {
            startEdge();
        }

        context.setAttribute("driver", driver);
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
        cartPage = new CartPage(driver);
        checkoutYourInformationPage = new CheckoutYourInformationPage(driver);
        checkoutOverviewPage = new CheckoutOverviewPage(driver);
        checkoutCompletePage = new CheckoutCompletePage(driver);
        soft = new SoftAssert();
    }

    /**
     * Метод выполняется после теста.
     * Закрывает запушенный веб-драйвер.
     */
    @Step("Закрытие браузера")
    @AfterMethod
    public void closeDriver() {
        driver.quit();
    }

    private void startChrome() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void startFirefox() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--guest");
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }

    private void startEdge() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        options.addArguments("--guest");
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
    }
}
