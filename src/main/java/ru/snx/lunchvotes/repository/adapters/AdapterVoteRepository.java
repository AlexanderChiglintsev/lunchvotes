package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.snx.lunchvotes.model.Vote;

import java.time.LocalDate;

public interface AdapterVoteRepository extends CrudRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.date = :localDate AND v.user.email = :userEmail")
    Vote getVoteByDate(@Param("localDate") LocalDate localDate,
                       @Param("userEmail") String userEmail
    );

    @Query("SELECT v FROM Vote v JOIN FETCH v.user u WHERE v.id = :voteId")
    Vote getVoteWithUser(@Param("voteId") Integer voteId);
}
