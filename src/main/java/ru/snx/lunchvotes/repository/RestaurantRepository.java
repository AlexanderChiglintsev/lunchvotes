package ru.snx.lunchvotes.repository;

import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save(Restaurant restaurant);

    Restaurant get(Integer id);

    List<Restaurant> getAll();

}
