package ru.snx.lunchvotes.utils.exceptions;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Votes received only before 11:00 a.m.!")
public class OutOfTimeException extends RuntimeException {

    public OutOfTimeException(String message) {
        super(message);
    }
}
