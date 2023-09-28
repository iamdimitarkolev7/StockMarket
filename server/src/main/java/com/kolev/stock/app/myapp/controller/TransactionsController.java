package com.kolev.stock.app.myapp.controller;

import com.kolev.stock.app.myapp.models.Transaction;
import com.kolev.stock.app.myapp.models.responses.Response;
import com.kolev.stock.app.myapp.service.interfaces.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class TransactionsController {

    private final TransactionService transactionService;

    @GetMapping("/api/users/transactions/{id}")
    public ResponseEntity<Response> getTransactions(@PathVariable("id") Long userId) {

        List<Transaction> transactions = transactionService.getAllTransactions(userId);

        return ResponseEntity.ok(
                Response.builder()
                        .data("transactions", transactions)
                        .message("All transactions retrieved successfully")
                        .success(true)
                        .build()
        );
    }
}
