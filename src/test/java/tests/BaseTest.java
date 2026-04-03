package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

/**
 * Базовый тест-класс
 */
public class BaseTest {

    WebDriver driver;

    /**
     * Метод выполняется перед тестом.
     * Настраивает условия открытия браузера Chrome для тестов.
     * "--start-maximized" - окно браузера во весь экран.
     * "--headless" - запускает браузер в безголовом режиме.
     * "--guest" - войти как гость в браузере.
     * Неявное ожидание 10 сек
     */
    @BeforeMethod
    public void startDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        //options.addArguments("--headless");
        options.addArguments("--guest");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
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
