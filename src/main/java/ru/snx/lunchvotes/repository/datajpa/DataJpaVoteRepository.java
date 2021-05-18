package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterUserRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterVoteRepository;

import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public class DataJpaVoteRepository implements VoteRepository {

    private final AdapterVoteRepository adapterVoteRepository;
    private final AdapterUserRepository adapterUserRepository;

    public DataJpaVoteRepository(AdapterVoteRepository adapterVoteRepository, AdapterUserRepository adapterUserRepository) {
        this.adapterVoteRepository = adapterVoteRepository;
        this.adapterUserRepository = adapterUserRepository;
    }

    @Override
    public Vote getByDate(LocalDate localDate, String userEmail) {
        return adapterVoteRepository.getVoteByDate(localDate, userEmail);
    }

    @Override
    public Vote get(Integer id) {
        return adapterVoteRepository.findById(id).orElse(null);
    }

    @Override
    public Vote getWithUser(Integer id) {
        return adapterVoteRepository.getVoteWithUser(id);
    }

    @Override
    @Transactional
    public Vote save(Vote vote, String userEmail) {
        if (vote.getUser() == null) vote.setUser(adapterUserRepository.getByEmail(userEmail));
        return adapterVoteRepository.save(vote);
    }
}
