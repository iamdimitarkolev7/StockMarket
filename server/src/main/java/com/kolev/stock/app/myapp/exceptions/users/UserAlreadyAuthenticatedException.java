package com.kolev.stock.app.myapp.exceptions.users;

public class UserAlreadyAuthenticatedException extends RuntimeException {

    public UserAlreadyAuthenticatedException(String message) {
        super(message);
    }
}
