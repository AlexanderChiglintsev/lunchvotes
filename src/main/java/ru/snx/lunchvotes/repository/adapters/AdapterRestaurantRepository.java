package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.repository.CrudRepository;
import ru.snx.lunchvotes.model.Restaurant;

public interface AdapterRestaurantRepository extends CrudRepository<Restaurant, Integer> {
}