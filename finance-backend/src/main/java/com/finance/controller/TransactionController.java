package com.finance.controller;

import com.finance.dto.TransactionRequest;
import com.finance.dto.TransactionResponse;
import com.finance.entity.Transaction;
import com.finance.service.TransactionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    private TransactionResponse toResponse(Transaction t) {
        return new TransactionResponse(
                t.getId(),
                t.getCategory().getId(),
                t.getCategory().getName(),
                t.getAmount(),
                String.valueOf(t.getType()),
                t.getDescription(),
                t.getTransactionDate()
        );
    }

    private List<TransactionResponse> toResponseList(List<Transaction> list) {
        return list.stream().map(this::toResponse).toList();
    }

    @PostMapping
    public ResponseEntity<TransactionResponse> createTransaction(
            @Valid @RequestBody TransactionRequest request) {

        Transaction saved = transactionService.createTransaction(request);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(toResponse(saved));
    }

    @GetMapping
    public ResponseEntity<List<TransactionResponse>> getAllTransactions() {
        return ResponseEntity.ok(
                toResponseList(transactionService.getAllTransactions()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionResponse> getTransactionById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                toResponse(transactionService.getTransactionById(id)));
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<TransactionResponse>> getTransactionsByUser(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                toResponseList(transactionService.getTransactionsByUser(userId)));
    }

    @GetMapping("/user/{userId}/expenses")
    public ResponseEntity<List<TransactionResponse>> getExpenses(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                toResponseList(transactionService.getExpensesByUser(userId)));
    }

    @GetMapping("/user/{userId}/income")
    public ResponseEntity<List<TransactionResponse>> getIncome(
            @PathVariable Long userId) {

        return ResponseEntity.ok(
                toResponseList(transactionService.getIncomeByUser(userId)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TransactionResponse> updateTransaction(
            @PathVariable Long id,
            @Valid @RequestBody TransactionRequest request) {

        return ResponseEntity.ok(
                toResponse(transactionService.updateTransaction(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(
            @PathVariable Long id) {

        transactionService.deleteTransaction(id);
        return ResponseEntity.noContent().build();
    }
}