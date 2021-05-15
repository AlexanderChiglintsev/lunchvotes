package ru.snx.lunchvotes.repository.datajpa;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.repository.AbstractTest;
import ru.snx.lunchvotes.repository.RestaurantRepository;

import java.util.List;

import static ru.snx.lunchvotes.utils.TestData.*;

class DataJpaRestaurantRepositoryTest extends AbstractTest {

    @Autowired
    RestaurantRepository restaurantRepository;

    @Test
    void save() {
        Restaurant saved = restaurantRepository.save(restaurantNew);
        Assertions.assertThat(restaurantNew)
                .usingRecursiveComparison()
                .isEqualTo(saved);
    }

    @Test
    void get() {
        Restaurant obtained = restaurantRepository.get(restaurantOne.getId());
        Assertions.assertThat(restaurantOne)
                .usingRecursiveComparison()
                .isEqualTo(obtained);
    }

    @Test
    void getAll() {
        List<Restaurant> obtained = restaurantRepository.getAll();
        Assertions.assertThat(restaurants)
                .usingRecursiveComparison()
                .isEqualTo(obtained);
    }
}