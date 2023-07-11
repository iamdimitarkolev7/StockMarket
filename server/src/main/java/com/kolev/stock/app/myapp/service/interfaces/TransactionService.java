package com.kolev.stock.app.myapp.service.interfaces;

import com.kolev.stock.app.myapp.models.Transaction;

import java.util.List;

public interface TransactionService {

    List<Transaction> getAllTransactions(Long userId);
}
