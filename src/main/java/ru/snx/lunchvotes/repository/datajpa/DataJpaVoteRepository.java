package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterVoteRepository;

import java.time.LocalDate;

@Repository
@Transactional(readOnly = true)
public class DataJpaVoteRepository implements VoteRepository {

    private final AdapterVoteRepository adapterVoteRepository;

    public DataJpaVoteRepository(AdapterVoteRepository adapterVoteRepository) {
        this.adapterVoteRepository = adapterVoteRepository;
    }

    @Override
    public Vote getByDate(LocalDate localDate, Integer userId) {
        return adapterVoteRepository.getVoteByDate(localDate, userId);
    }

    @Override
    public Vote get(Integer id) {
        return adapterVoteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
        return adapterVoteRepository.save(vote);
    }
}
