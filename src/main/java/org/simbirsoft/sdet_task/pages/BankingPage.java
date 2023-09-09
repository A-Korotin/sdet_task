package org.simbirsoft.sdet_task.pages;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.temporal.ChronoUnit;


@RequiredArgsConstructor
public class BankingPage {
    private final RemoteWebDriver driver;
    private final WebDriverWait wait;


    public BankingPage openLoginPage() {
        // Откройте страницу https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login
        driver.get("https://www.globalsqa.com/angularJs-protractor/BankingProject/#/login");
        driver.executeScript("window.localStorage.clear();");
        return this;
    }
    private WebElement getElement(By locator) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public BankingPage loginAs(String username) {
        getElement(By.xpath("//button[@class=\"btn btn-primary btn-lg\" and text()=\"Customer Login\"]")).click();
        getElement(By.xpath("//*[@id=\"userSelect\"]")).sendKeys(username);
        getElement(By.xpath("/html/body/div/div/div[2]/div/form/button")).click();
        return this;
    }

    public BankingPage deposit(long amount) {
        getElement(By.xpath("//button[@ng-class=\"btnClass2\"]")).click();
        getElement(By.xpath("//input[@placeholder=\"amount\" ]")).sendKeys(String.valueOf(amount));
        getElement(By.xpath("//button[@type=\"submit\" and text()=\"Deposit\"]")).click();
        return this;
    }
    @SneakyThrows
    public BankingPage withdraw(long amount) {
        getElement(By.xpath("//button[@ng-class=\"btnClass3\"]")).click();
        getElement(By.xpath("//label[text()=\"Amount to be Withdrawn :\" ]")); // wait until page reloads
        getElement(By.xpath("//input[@placeholder=\"amount\" ]")).sendKeys(String.valueOf(amount));
        getElement(By.xpath("//button[@type=\"submit\" and text()=\"Withdraw\"]")).click();
        return this;
    }
    public long checkBalance() {
        String amount = getElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();
        return Long.parseLong(amount);
    }

    public BankingPage openTransactionsPage() {
        getElement(By.xpath("/html/body/div/div/div[2]/div/div[3]/button[1]")).click();
        return this;
    }

    public BankingPage clearAndReturn() {
        try {
            getElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[2]")).click();
        } catch (ElementNotInteractableException ignored) {} // transaction list is empty
        getElement(By.xpath("/html/body/div/div/div[2]/div/div[1]/button[1]")).click();
        return this;
    }

    public BankingPage checkTransactions() {
        // Проверьте наличие транзакций
        // ...
        return this;
    }

    public BankingPage exportTransactionsToCSV() {
        // Сформируйте и экспортируйте файл CSV
        // ...
        return this;
    }
}

