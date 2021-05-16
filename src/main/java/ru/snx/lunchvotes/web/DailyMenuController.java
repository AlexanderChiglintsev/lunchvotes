package ru.snx.lunchvotes.web;

import org.springframework.cache.annotation.CacheEvict;
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
import ru.snx.lunchvotes.utils.ToConverter;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.snx.lunchvotes.utils.LimitationChecker.checkExistAndTodayDailyMenu;
import static ru.snx.lunchvotes.utils.LimitationChecker.checkExistRestaurant;

@RestController
@RequestMapping(value = "/dailyMenus", produces = MediaType.APPLICATION_JSON_VALUE)
public class DailyMenuController {
    private final DailyMenuRepository dailyMenuRepository;
    private final RestaurantRepository restaurantRepository;

    public DailyMenuController(DailyMenuRepository dailyMenuRepository, RestaurantRepository restaurantRepository) {
        this.dailyMenuRepository = dailyMenuRepository;
        this.restaurantRepository = restaurantRepository;
    }

    @GetMapping("/{id}")
    public DailyMenu get(@PathVariable Integer id) {
        return dailyMenuRepository.get(id);
    }

    @GetMapping
    public List<DailyMenuTo> getTodayMenus() {
        return ToConverter.getDailyMenuTo(dailyMenuRepository.getAll(LocalDate.now()));
    }

    @PostMapping
    public ResponseEntity<DailyMenu> createDailyMenu(@RequestParam Integer restaurantId) {
        Restaurant restaurant = restaurantRepository.get(restaurantId);
        checkExistRestaurant(restaurant);
        DailyMenu created = dailyMenuRepository.save(new DailyMenu(null, restaurant, LocalDate.now()));

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/dailyMenus/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping("/{id}")
    @CacheEvict(value = "todayMenus", allEntries = true)
    public ResponseEntity<DailyMenu> addDish(@PathVariable Integer id, @RequestBody Dish dish) {
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
