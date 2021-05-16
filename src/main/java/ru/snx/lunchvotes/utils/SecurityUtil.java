package ru.snx.lunchvotes.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    public static String authUserEmail() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        requireNonNull(auth, "No authorized user found");
        Object principal = auth.getPrincipal();
        if (principal instanceof User) return ((User) principal).getUsername();
        throw new NullPointerException("No authorized user found");
    }
}
