package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterVoteRepository;

@Repository
@Transactional(readOnly = true)
public class DataJpaVoteRepository implements VoteRepository {

    private final AdapterVoteRepository adapterVoteRepository;

    public DataJpaVoteRepository(AdapterVoteRepository adapterVoteRepository) {
        this.adapterVoteRepository = adapterVoteRepository;
    }

    @Override
    @Transactional
    public Vote save(Vote vote) {
        return adapterVoteRepository.save(vote);
    }
}
