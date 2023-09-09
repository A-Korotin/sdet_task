package org.simbirsoft.sdet_task;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simbirsoft.sdet_task.pages.BankingPage;
import org.simbirsoft.sdet_task.service.FibonacciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URL;
import java.time.Duration;
import java.time.LocalDate;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BankingTest {

    private WebDriver driver;
    private WebDriverWait wait;
    private BankingPage bankingPage;

    @Value("${selenium.grid.url}")
    private String gridUrl;

    @Autowired
    private FibonacciService fibonacciService;

    @BeforeAll
    public void setup() throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "116.0");
        chromeOptions.setCapability("platformName", "linux");
        chromeOptions.setCapability("se:name", "My simple test");
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--incognito");
        var driver = new RemoteWebDriver(new URL(gridUrl), chromeOptions);
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        bankingPage = new BankingPage(driver, wait);
    }

    @Test
    @Order(1)
    public void login_ThenCheckBalance() {
        bankingPage.openLoginPage()
                .loginAs("Harry Potter");

        long balance = bankingPage.checkBalance();

        Assertions.assertEquals(0, balance);
    }

    @Test
    @Order(2)
    public void deposit_ThenWithdraw_ThenCheckBalance() {
        int currentDayOfMonth = LocalDate.now().getDayOfMonth();
        long amount = fibonacciService.calculateFibonacci(currentDayOfMonth);

        bankingPage.deposit(amount);
        bankingPage.withdraw(amount);
        long balance = bankingPage.checkBalance();
        Assertions.assertEquals(0, balance);
    }

    @AfterAll
    public void teardown() {
        bankingPage.openTransactionsPage().clearAndReturn();
        driver.quit();
    }
}
