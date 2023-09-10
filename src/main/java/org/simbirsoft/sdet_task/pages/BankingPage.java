package org.simbirsoft.sdet_task.pages;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.simbirsoft.sdet_task.domain.Transaction;

import java.util.List;


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
        // wait until the withdrawal page appears
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[text()=\"Amount to be Withdrawn :\"]")));
        getElement(By.xpath("//input[@placeholder=\"amount\" ]")).sendKeys(String.valueOf(amount));
        getElement(By.xpath("//button[@type=\"submit\" and text()=\"Withdraw\"]")).click();
        return this;
    }
    public long checkBalance() {
        String amount = getElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/strong[2]")).getText();
        return Long.parseLong(amount);
    }

    public BankingPage openTransactionsPage() {
        getElement(By.xpath("//button[@ng-class=\"btnClass1\"]")).click();
        return this;
    }

    public List<Transaction> retrieveTransactions() {
        // wait until the transaction table loads
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//table/tbody")));

        return wait.until(ExpectedConditions
                        .presenceOfAllElementsLocatedBy((By.xpath("//tr[@class=\"ng-scope\"]"))))
                .stream()
                .map(Transaction::ofWebElement)
                .toList();
    }
}

