package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.snx.lunchvotes.model.DailyMenu;

import java.time.LocalDate;
import java.util.List;

public interface AdapterDailyMenuRepository extends JpaRepository<DailyMenu, Integer> {

    @Query("SELECT dm FROM DailyMenu dm WHERE dm.date = :localDate")
    List<DailyMenu> getAllDailyMenuByDate(@Param("localDate") LocalDate localDate);

}
