package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface AdapterVoteRepository extends JpaRepository<Vote, Integer> {

    @Query("SELECT v FROM Vote v WHERE v.date = :localDate AND v.user.id = :userId")
    Vote getVoteByDate(@Param("localDate") LocalDate localDate,
                       @Param("userId") Integer userId
    );

}