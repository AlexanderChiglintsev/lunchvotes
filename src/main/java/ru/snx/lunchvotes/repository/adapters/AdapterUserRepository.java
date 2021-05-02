package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snx.lunchvotes.model.User;

public interface AdapterUserRepository extends JpaRepository<User, Integer> {

    User getByEmail(String email);
}