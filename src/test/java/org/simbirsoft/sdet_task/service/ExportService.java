package org.simbirsoft.sdet_task.service;

import org.simbirsoft.sdet_task.domain.Transaction;

import java.util.List;

public interface ExportService {
    void export(List<Transaction> transactions);
}
