package com.kolev.stock.app.myapp.exceptions.wallets;

public class NotEnoughMoneyException extends RuntimeException {

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
