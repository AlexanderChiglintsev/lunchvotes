package ru.snx.lunchvotes.repository.datajpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.AbstractTest;
import ru.snx.lunchvotes.repository.VoteRepository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.snx.lunchvotes.utils.TestData.*;

class DataJpaVoteRepositoryTest extends AbstractTest {

    @Autowired
    private VoteRepository voteRepository;

    @Test
    void save() {
        Vote saved = voteRepository.save(newVote, admin.getEmail());
        Assertions.assertThat(newVote)
                .usingRecursiveComparison()
                .ignoringFields("user", "dailyMenu")
                .isEqualTo(saved);
    }

    @Test
    void saveWithExistingDateAndUser() {
        Vote copy = new Vote(vote1);
        copy.setId(null);
        copy.setUser(admin);
        assertThrows(DataIntegrityViolationException.class, () -> voteRepository.save(copy, user.getEmail()));
    }

    @Test
    void saveNotValid() {
        checkValidation(() -> voteRepository.save(new Vote(null, dm1, null, user), user.getEmail()));
    }

    @Test
    void getById() {
        Vote obtained = voteRepository.get(vote1.getId());
        Assertions.assertThat(vote1)
                .usingRecursiveComparison()
                .ignoringFields("user", "dailyMenu")
                .isEqualTo(obtained);
    }

    @Test
    void getByDate() {
        Vote obtained = voteRepository.getByDate(vote1.getDate(), vote1.getUser().getEmail());
        Assertions.assertThat(vote1)
                .usingRecursiveComparison()
                .ignoringFields("user", "dailyMenu")
                .isEqualTo(obtained);
    }

    @Test
    void getByDateNotExist() {
        Vote obtained = voteRepository.getByDate(vote1.getDate(), vote1.getUser().getEmail());
        Assertions.assertThat(vote1)
                .usingRecursiveComparison()
                .ignoringFields("user", "dailyMenu")
                .isEqualTo(obtained);
    }
}