package ru.snx.lunchvotes.repository;

import ru.snx.lunchvotes.model.User;

public interface UserRepository {

    User save(User user);

    // null if not found
    User getByEmail(String email);
}
