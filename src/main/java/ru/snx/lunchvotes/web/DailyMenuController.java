package ru.snx.lunchvotes.web;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.to.DailyMenuTo;
import ru.snx.lunchvotes.utils.DailyMenuConverter;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/dailyMenus", produces = MediaType.APPLICATION_JSON_VALUE)
public class DailyMenuController {
    private final DailyMenuRepository dailyMenuRepository;

    public DailyMenuController(DailyMenuRepository dailyMenuRepository) {
        this.dailyMenuRepository = dailyMenuRepository;
    }

    @GetMapping
    public List<DailyMenuTo> getTodayMenus(){
        return DailyMenuConverter.convert(dailyMenuRepository.getAllWithVotes(LocalDate.of(2021, 5, 1)));
    }

    public DailyMenu createDailyMenu(){
        return null;
    }
}
