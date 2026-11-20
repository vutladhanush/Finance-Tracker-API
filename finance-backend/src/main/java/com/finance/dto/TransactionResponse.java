package com.finance.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionResponse {

    private Long id;
    private Long categoryId;
    private String categoryName;
    private BigDecimal amount;
    private String type;
    private String description;
    private LocalDate transactionDate;

    public TransactionResponse(Long id, Long categoryId, String categoryName,
                               BigDecimal amount, String type,
                               String description, LocalDate transactionDate) {
        this.id = id;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.amount = amount;
        this.type = type;
        this.description = description;
        this.transactionDate = transactionDate;
    }

    public Long getId() { return id; }
    public Long getCategoryId() { return categoryId; }
    public String getCategoryName() { return categoryName; }
    public BigDecimal getAmount() { return amount; }
    public String getType() { return type; }
    public String getDescription() { return description; }
    public LocalDate getTransactionDate() { return transactionDate; }
}