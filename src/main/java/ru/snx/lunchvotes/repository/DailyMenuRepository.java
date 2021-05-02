package ru.snx.lunchvotes.repository;

import ru.snx.lunchvotes.model.DailyMenu;

import java.time.LocalDate;
import java.util.List;

public interface DailyMenuRepository {

    DailyMenu save(DailyMenu dailyMenu);

    List<DailyMenu> getAllDailyMenu(LocalDate localDate);


}
