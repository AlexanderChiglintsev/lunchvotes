package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.repository.UserRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterUserRepository;

@Repository
public class DataJpaUserRepository implements UserRepository {

    private final AdapterUserRepository adapterUserRepository;

    public DataJpaUserRepository(AdapterUserRepository adapterUserRepository) {
        this.adapterUserRepository = adapterUserRepository;
    }

    @Override
    public User save(User user) {
        return adapterUserRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        return adapterUserRepository.getByEmail(email);
    }
}
