package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterDailyMenuRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaDailyMenuRepository implements DailyMenuRepository {

    private final AdapterDailyMenuRepository adapterDailyMenuRepository;

    public DataJpaDailyMenuRepository(AdapterDailyMenuRepository adapterDailyMenuRepository) {
        this.adapterDailyMenuRepository = adapterDailyMenuRepository;
    }

    @Transactional
    @Override
    public DailyMenu save(DailyMenu dailyMenu) {
        return adapterDailyMenuRepository.save(dailyMenu);
    }

    @Override
    public DailyMenu get(Integer id) {
        return adapterDailyMenuRepository.findById(id).orElse(null);
    }

    @Override
    public List<DailyMenu> getAll(LocalDate localDate) {
        return adapterDailyMenuRepository.findAllByDate(localDate);
    }

    @Override
    public List<DailyMenu> getAllWithVotes(LocalDate localDate) {
        return adapterDailyMenuRepository.getAllByDateWithVotes(localDate);
    }
}
