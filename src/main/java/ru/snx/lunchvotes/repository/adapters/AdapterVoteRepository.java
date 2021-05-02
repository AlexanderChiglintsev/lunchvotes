package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snx.lunchvotes.model.Vote;

public interface AdapterVoteRepository extends JpaRepository<Vote, Integer> {
}
