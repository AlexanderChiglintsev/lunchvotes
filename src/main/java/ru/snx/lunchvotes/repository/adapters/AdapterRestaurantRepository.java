package ru.snx.lunchvotes.repository.adapters;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.model.User;

public interface AdapterRestaurantRepository extends JpaRepository<Restaurant, Integer> {
}