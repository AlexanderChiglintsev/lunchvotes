package ru.snx.lunchvotes.repository.datajpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.repository.AbstractTest;
import ru.snx.lunchvotes.repository.DailyMenuRepository;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static ru.snx.lunchvotes.utils.TestData.*;

class DataJpaDailyMenuRepositoryTest extends AbstractTest {

    @Autowired
    private DailyMenuRepository dailyMenuRepository;

    @Test
    void save() {
        DailyMenu saved = dailyMenuRepository.save(newDailyMenu);
        Assertions.assertThat(newDailyMenu)
                .usingRecursiveComparison()
                .ignoringFields("votes", "dailyDishes.dailyMenu")
                .isEqualTo(saved);
    }

    @Test
    void getAll() {
        List<DailyMenu> obtained = dailyMenuRepository.getAll(LocalDate.of(2021, 5, 1));
        Assertions.assertThat(dailyMenus)
                .usingRecursiveComparison()
                .ignoringFields("votes", "dailyDishes.dailyMenu")
                .isEqualTo(obtained);
    }

    @Test
    void get() {
        DailyMenu obtained = dailyMenuRepository.get(dm1.getId());
        Assertions.assertThat(dm1)
                .usingRecursiveComparison()
                .ignoringFields("votes", "dailyDishes.dailyMenu")
                .isEqualTo(obtained);
    }

    @Test
    void getAllWithVotes() {
        List<DailyMenu> obtained = dailyMenuRepository.getAllWithVotes(LocalDate.of(2021, 5, 1));
        Assertions.assertThat(dailyMenus)
                .usingRecursiveComparison()
                .ignoringFields("dailyDishes.dailyMenu", "votes.dailyMenu", "votes.user")
                .isEqualTo(obtained);
    }

    @Test
    void saveNotValid() {
        checkValidation(() -> dailyMenuRepository.save(new DailyMenu(null, restaurantOne, null, Collections.emptyList())));
    }
}