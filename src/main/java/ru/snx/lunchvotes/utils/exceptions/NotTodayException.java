package ru.snx.lunchvotes.utils.exceptions;

public class NotTodayException extends RuntimeException {

    public NotTodayException(String message) {
        super(message);
    }
}
