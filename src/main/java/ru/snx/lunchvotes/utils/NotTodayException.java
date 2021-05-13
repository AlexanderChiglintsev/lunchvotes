package ru.snx.lunchvotes.utils;

public class NotTodayException extends RuntimeException {

    public NotTodayException(String message) {
        super(message);
    }
}
