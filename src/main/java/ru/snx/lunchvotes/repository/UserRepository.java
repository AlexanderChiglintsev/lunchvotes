package ru.snx.lunchvotes.repository;

import ru.snx.lunchvotes.model.User;

import java.util.List;

public interface UserRepository {

    User save(User user);

    User get(Integer id);

    List<User> getAll();

    User getByEmail(String email);
}
