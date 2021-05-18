package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.repository.CrudRepository;
import ru.snx.lunchvotes.model.User;

public interface AdapterUserRepository extends CrudRepository<User, Integer> {

    User getByEmail(String email);
}