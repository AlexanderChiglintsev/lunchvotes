package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterVoteRepository;

@Repository
public class DataJpaVoteRepository implements VoteRepository {

    private final AdapterVoteRepository adapterVoteRepository;

    public DataJpaVoteRepository(AdapterVoteRepository adapterVoteRepository) {
        this.adapterVoteRepository = adapterVoteRepository;
    }

    @Override
    public Vote save(Vote vote) {
        return adapterVoteRepository.save(vote);
    }
}
