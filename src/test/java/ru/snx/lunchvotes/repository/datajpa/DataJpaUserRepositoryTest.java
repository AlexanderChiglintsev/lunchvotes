package ru.snx.lunchvotes.repository.datajpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.repository.AbstractTest;
import ru.snx.lunchvotes.repository.UserRepository;
import ru.snx.lunchvotes.utils.NotFoundException;

import javax.validation.ConstraintViolationException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.snx.lunchvotes.utils.TestData.newUser;
import static ru.snx.lunchvotes.utils.TestData.user;

class DataJpaUserRepositoryTest extends AbstractTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void save() {
        User saved = userRepository.save(newUser);
        Assertions.assertThat(newUser)
                .usingRecursiveComparison()
                .ignoringFields("votes")
                .isEqualTo(saved);
    }

    @Test
    void saveWithExistingEmail() {
        User copy = new User(user);
        copy.setId(null);
        assertThrows(DataIntegrityViolationException.class, () -> userRepository.save(copy));
    }

    @Test
    void getByEmail() {
        User obtained = userRepository.getByEmail(user.getEmail());
        Assertions.assertThat(user)
                .usingRecursiveComparison()
                .ignoringFields("votes")
                .isEqualTo(obtained);
    }

    @Test
    void getNotSaved() {
        assertThrows(NotFoundException.class, () -> userRepository.getByEmail(""));
    }

    @Test
    void saveNotValid() {
        User notValid = new User(user);
        notValid.setName("");
        notValid.setEmail("");
        notValid.setPassword("1");
        assertThrows(ConstraintViolationException.class, () -> userRepository.save(notValid));
    }

}