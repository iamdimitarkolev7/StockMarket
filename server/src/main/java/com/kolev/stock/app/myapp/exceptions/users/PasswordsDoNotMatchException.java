package com.kolev.stock.app.myapp.exceptions.users;

public class PasswordsDoNotMatchException extends RuntimeException {

    public PasswordsDoNotMatchException(String message) {
        super(message);
    }
}
