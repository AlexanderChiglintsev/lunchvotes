package ru.snx.lunchvotes.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Votes received only before 11:00 a.m.!")
public class OutOfTimeException extends RuntimeException {

    public OutOfTimeException(String message) {
        super(message);
    }
}
