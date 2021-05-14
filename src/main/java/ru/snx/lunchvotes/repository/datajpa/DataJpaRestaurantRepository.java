package ru.snx.lunchvotes.repository.datajpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.repository.RestaurantRepository;
import ru.snx.lunchvotes.repository.adapters.AdapterRestaurantRepository;

import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DataJpaRestaurantRepository implements RestaurantRepository {

    private final AdapterRestaurantRepository adapterRestaurantRepository;

    public DataJpaRestaurantRepository(AdapterRestaurantRepository adapterRestaurantRepository) {
        this.adapterRestaurantRepository = adapterRestaurantRepository;
    }

    @Override
    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return adapterRestaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant get(Integer id) {
        return adapterRestaurantRepository.findById(id).orElse(null);
    }

    @Override
    public List<Restaurant> getAll() {
        return adapterRestaurantRepository.findAll();
    }
}
