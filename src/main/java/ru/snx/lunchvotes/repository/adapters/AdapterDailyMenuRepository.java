package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.snx.lunchvotes.model.DailyMenu;

import java.time.LocalDate;
import java.util.List;

public interface AdapterDailyMenuRepository extends CrudRepository<DailyMenu, Integer> {

    List<DailyMenu> findAllByDate(LocalDate localDate);

    @Query("SELECT DISTINCT dm FROM DailyMenu dm LEFT JOIN FETCH dm.votes WHERE dm.date = :localDate")
    List<DailyMenu> getAllByDateWithVotes(@Param("localDate") LocalDate localDate);
}
