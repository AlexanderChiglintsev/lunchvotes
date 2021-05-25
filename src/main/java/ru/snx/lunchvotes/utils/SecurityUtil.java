package ru.snx.lunchvotes.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import ru.snx.lunchvotes.model.Role;

import java.util.stream.Collectors;

import static java.util.Objects.requireNonNull;

public class SecurityUtil {

    public static ru.snx.lunchvotes.model.User getAuthUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        requireNonNull(auth, "No authorized user found");
        Object principal = auth.getPrincipal();
        if (principal instanceof User) return convertToAppUser((User) principal);
        throw new NullPointerException("No authorized user found");
    }

    private static ru.snx.lunchvotes.model.User convertToAppUser(User regUser) {
        ru.snx.lunchvotes.model.User user = new ru.snx.lunchvotes.model.User(
                null,
                "authorized",
                regUser.getUsername(),
                null
        );
        user.setRoles(
                regUser.getAuthorities().stream()
                .map(auth -> Role.valueOf(auth.getAuthority()))
                .collect(Collectors.toSet())
        );
        return user;
    }
}
