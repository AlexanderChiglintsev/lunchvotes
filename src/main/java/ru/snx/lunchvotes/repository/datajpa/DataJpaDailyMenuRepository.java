package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterDailyMenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDailyMenuRepository implements DailyMenuRepository {

    private final AdapterDailyMenuRepository adapterDailyMenuRepository;

    public DataJpaDailyMenuRepository(AdapterDailyMenuRepository adapterDailyMenuRepository) {
        this.adapterDailyMenuRepository = adapterDailyMenuRepository;
    }

    @Override
    public DailyMenu save(DailyMenu dailyMenu) {
        return adapterDailyMenuRepository.save(dailyMenu);
    }

    @Override
    public List<DailyMenu> getAllDailyMenu(LocalDate localDate) {
        return adapterDailyMenuRepository.getAllDailyMenuByDate(localDate);
    }
}
