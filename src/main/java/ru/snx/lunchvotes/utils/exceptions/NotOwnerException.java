package ru.snx.lunchvotes.utils.exceptions;

public class NotOwnerException extends RuntimeException {

    public NotOwnerException(String message) {
        super(message);
    }
}
