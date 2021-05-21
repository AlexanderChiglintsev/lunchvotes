package ru.snx.lunchvotes.utils.exceptions;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Request not from owner!")
public class NotOwnerException extends RuntimeException {

    public NotOwnerException(String message) {
        super(message);
    }
}
