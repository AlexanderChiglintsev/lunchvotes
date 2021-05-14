package ru.snx.lunchvotes.utils;

import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Dish;
import ru.snx.lunchvotes.to.DailyMenuTo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DailyMenuConverter {

    public static List<DailyMenuTo> convert(List<DailyMenu> dailyMenus){
        if (dailyMenus != null) {
            return dailyMenus.stream().map((dm) -> new DailyMenuTo(
                    dm.getId(),
                    dm.getRestaurant().getName(),
                    dm.getVotes().size(),
                    dm.getDailyDishes().stream().mapToInt(Dish::getCost).sum(),
                    dm.getDailyDishes()
            )).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
