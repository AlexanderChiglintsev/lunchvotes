package ru.snx.lunchvotes.utils;

public class OutOfTimeException extends RuntimeException {

    public OutOfTimeException(String message) {
        super(message);
    }
}
