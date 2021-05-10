package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.repository.UserRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterUserRepository;
import ru.snx.lunchvotes.utils.NotFoundException;

@Repository
@Transactional(readOnly = true)
public class DataJpaUserRepository implements UserRepository {

    private final AdapterUserRepository adapterUserRepository;

    public DataJpaUserRepository(AdapterUserRepository adapterUserRepository) {
        this.adapterUserRepository = adapterUserRepository;
    }

    @Override
    @Transactional
    public User save(User user) {
        return adapterUserRepository.save(user);
    }

    @Override
    public User getByEmail(String email) {
        User result = adapterUserRepository.getByEmail(email);
        if (result == null) throw new NotFoundException("User with such email not found!");
        return result;
    }
}
