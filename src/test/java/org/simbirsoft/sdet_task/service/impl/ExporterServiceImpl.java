package org.simbirsoft.sdet_task.service.impl;

import org.simbirsoft.sdet_task.domain.Transaction;
import org.simbirsoft.sdet_task.service.ExportService;
import org.springframework.stereotype.Service;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@Service("csvExporter")
public class ExporterServiceImpl implements ExportService {

    @Override
    public void export(List<Transaction> transactions) {
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream("/resources/export.csv"))) {
            for (Transaction t: transactions) {
                StringBuilder builder = new StringBuilder();
                builder.append(t.getDateTime()
                        .format(DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss", Locale.ENGLISH)))
                        .append(',')
                        .append(t.getAmount())
                        .append(',')
                        .append(t.getType().name().substring(0, 1).toUpperCase())
                        .append(t.getType().name().substring(1))
                        .append('\n');
                outputStream.write(builder.toString().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
