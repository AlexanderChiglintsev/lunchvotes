package ru.snx.lunchvotes.utils.exceptions;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Vote requests only for today's menu!")
public class NotTodayException extends RuntimeException {

    public NotTodayException(String message) {
        super(message);
    }
}
