package com.kolev.stock.app.myapp.exceptions.wallets;

public class WalletNotFoundException extends RuntimeException {

    public WalletNotFoundException(String message) {
        super(message);
    }
}
