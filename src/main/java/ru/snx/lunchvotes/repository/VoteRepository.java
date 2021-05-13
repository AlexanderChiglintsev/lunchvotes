package ru.snx.lunchvotes.repository;

import ru.snx.lunchvotes.model.Vote;

import java.time.LocalDate;

public interface VoteRepository {

    Vote getByDate(LocalDate localDate, Integer userId);

    Vote get(Integer id);

    Vote save(Vote vote);
}
