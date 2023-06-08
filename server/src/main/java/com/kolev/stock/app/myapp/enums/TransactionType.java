package com.kolev.stock.app.myapp.enums;

public enum TransactionType {

    BUY("BUY"),
    SELL("SELL"),
    ADD_MONEY("ADD_MONEY"),
    WITHDRAW_MONEY("WITHDRAW_MONEY");

    private final String transactionType;

    TransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionType() {
        return transactionType;
    }
}
