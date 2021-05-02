package ru.snx.lunchvotes.repository;

import ru.snx.lunchvotes.model.Vote;

public interface VoteRepository {

    Vote save(Vote vote);
}
