package com.epam.training.sportsbeatting.exeptions;

public class UserWithSuchEmailExistsException extends IllegalArgumentException {

    public UserWithSuchEmailExistsException(String message) {
        super(message);
    }
}
