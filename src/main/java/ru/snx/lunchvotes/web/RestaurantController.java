package ru.snx.lunchvotes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.repository.RestaurantRepository;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/restaurants", produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {

    public final Logger LOG = LoggerFactory.getLogger(getClass());

    private final RestaurantRepository restaurantRepository;

    public RestaurantController(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable Integer id) {
        LOG.debug("Get restaurant with id = {}", id);
        return restaurantRepository.get(id);
    }

    @GetMapping
    public List<Restaurant> getAll() {
        LOG.debug("Get all restaurants");
        return restaurantRepository.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> save(@Valid @RequestBody Restaurant restaurant) {
        if (restaurant.getId() == null) {
            LOG.debug("Save new restaurant");
        } else {
            LOG.debug("Update restaurant with id = {}", restaurant.getId());
        }
        Restaurant created = restaurantRepository.save(restaurant);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/restaurants/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
