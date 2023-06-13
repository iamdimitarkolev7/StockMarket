package com.kolev.stock.app.myapp.exceptions.users;

public class UserDoesNotExistException extends RuntimeException {

    public UserDoesNotExistException(String message) {
        super(message);
    }
}
