package ru.snx.lunchvotes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Dish;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.RestaurantRepository;
import ru.snx.lunchvotes.to.DailyMenuTo;
import ru.snx.lunchvotes.utils.DateContainer;
import ru.snx.lunchvotes.utils.ToConverter;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.snx.lunchvotes.utils.LimitationChecker.checkExistAndTodayDailyMenu;
import static ru.snx.lunchvotes.utils.LimitationChecker.checkExistRestaurant;

@RestController
@RequestMapping(value = "/dailyMenus", produces = MediaType.APPLICATION_JSON_VALUE)
public class DailyMenuController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final DailyMenuRepository dailyMenuRepository;
    private final RestaurantRepository restaurantRepository;

    public DailyMenuController(DailyMenuRepository dailyMenuRepository, RestaurantRepository restaurantRepository) {
        this.dailyMenuRepository = dailyMenuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    public DailyMenu get(@PathVariable Integer id) {
        LOG.debug("Get DailyMenu with id = {}", id);
        return dailyMenuRepository.get(id);
    }

    @GetMapping
    public List<DailyMenuTo> getTodayMenus() {
        LOG.debug("Get today's menus");
        return ToConverter.getDailyMenuTo(dailyMenuRepository.getAll(DateContainer.getDate()));
    }

    @PostMapping
    public ResponseEntity<DailyMenu> createDailyMenu(@RequestParam Integer restaurantId) {
        LOG.debug("Create new DailyMenu");
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        checkExistRestaurant(restaurant);
        DailyMenu created = dailyMenuRepository.save(new DailyMenu(null, restaurant, DateContainer.getDate()));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dailyMenus/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DailyMenu> addDish(@PathVariable Integer id, @Valid @RequestBody Dish dish) {
        LOG.debug("Add Dish to DailyMenu with id = {}", id);
        DailyMenu dailyMenu = dailyMenuRepository.get(id);
        checkExistAndTodayDailyMenu(dailyMenu);
        dailyMenu.addDish(dish);
        DailyMenu updated = dailyMenuRepository.save(dailyMenu);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dailyMenus/{id}")
                .buildAndExpand(updated.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(updated);
    }
}
