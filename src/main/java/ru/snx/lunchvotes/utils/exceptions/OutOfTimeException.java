package ru.snx.lunchvotes.utils.exceptions;

public class OutOfTimeException extends RuntimeException {

    public OutOfTimeException(String message) {
        super(message);
    }
}
