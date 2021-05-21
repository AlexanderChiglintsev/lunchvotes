package ru.snx.lunchvotes.utils.exceptions;

//@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No data found!")
public class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }
}
