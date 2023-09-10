package org.simbirsoft.sdet_task.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Transaction {
    private LocalDateTime dateTime;
    private long amount;
    private TransactionType type;

    public static Transaction ofWebElement(WebElement webElement) {
        List<WebElement> columns = webElement.findElements(By.xpath("*")); // all child elements
        LocalDateTime dateTime = LocalDateTime.parse(columns.get(0).getText(),
                DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a", Locale.ENGLISH));
        long amount = Long.parseLong(columns.get(1).getText());
        TransactionType type = TransactionType.valueOf(columns.get(2).getText().toUpperCase());
        return Transaction.builder()
                .dateTime(dateTime)
                .amount(amount)
                .type(type)
                .build();
    }
}
