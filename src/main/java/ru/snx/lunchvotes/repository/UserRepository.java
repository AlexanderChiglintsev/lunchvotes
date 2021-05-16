package ru.snx.lunchvotes.repository;

import ru.snx.lunchvotes.model.User;

public interface UserRepository {

    User save(User user);

    User get(Integer id);

    User getByEmail(String email);
}
