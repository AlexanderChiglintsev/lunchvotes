package ru.snx.lunchvotes.web;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.snx.lunchvotes.utils.LimitationChecker;
import ru.snx.lunchvotes.utils.exceptions.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class AllExceptionsHandler {

    private static final Map<String, String> CONSTRAINTS = Map.of(
            "users_email_unique_idx", "User with such email already exist!",
            "user_roles_unique_idx", "User can be with once role!",
            "restaurant_name_unique_idx", "Restaurant with such name already exist!",
            "restaurant_date_unique_idx", "Daily menu for this restaurant already exist, change it!",
            "date_user_unique_idx", "Votes from user already saved today!",
            "dish_daily_menu_unique_idx", "Such dish already exist in this daily menu!"
    );

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorInfo> handleNotFoundError(HttpServletRequest req, NotFoundException e) {
        return getErrorInfo(HttpStatus.NOT_FOUND, req.getRequestURL(), e.getMessage());
    }

    @ExceptionHandler({NotOwnerException.class, NotTodayException.class, OutOfTimeException.class})
    public ResponseEntity<ErrorInfo> handleOwnerAndDateTimeError(HttpServletRequest req, Exception e) {
        return getErrorInfo(HttpStatus.BAD_REQUEST, req.getRequestURL(), e.getMessage());
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorInfo> bindValidationError(HttpServletRequest req, BindException e) {
        String[] errors = e.getBindingResult().getFieldErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toArray(String[]::new);
        return getErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, req.getRequestURL(), errors);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorInfo> conflict(HttpServletRequest req, DataIntegrityViolationException e) {
        String rootMsg = LimitationChecker.getRootCause(e).getMessage();
        if (rootMsg != null) {
            String lowerCaseMsg = rootMsg.toLowerCase();
            for (Map.Entry<String, String> entry : CONSTRAINTS.entrySet()) {
                if (lowerCaseMsg.contains(entry.getKey())) {
                    return getErrorInfo(HttpStatus.UNPROCESSABLE_ENTITY, req.getRequestURL(), entry.getValue());
                }
            }
        }
        return getErrorInfo(HttpStatus.CONFLICT, req.getRequestURL(), "Incorrect data, can't save!");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> handleError(HttpServletRequest req, Exception e) {
        return getErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR, req.getRequestURL(), "Internal server error!");
    }

    private ResponseEntity<ErrorInfo> getErrorInfo(HttpStatus httpStatus, CharSequence url, String[] errors) {
        return ResponseEntity.status(httpStatus)
                .body(new ErrorInfo(url, errors));
    }

    private ResponseEntity<ErrorInfo> getErrorInfo(HttpStatus httpStatus, CharSequence url, String error) {
        return getErrorInfo(httpStatus, url, new String[]{error});
    }
}
