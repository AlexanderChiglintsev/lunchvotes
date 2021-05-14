package ru.snx.lunchvotes.utils;

import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Dish;
import ru.snx.lunchvotes.to.DailyMenuTo;
import ru.snx.lunchvotes.to.VoteResultTo;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ToConverter {

    public static List<DailyMenuTo> getDailyMenuTo(List<DailyMenu> dailyMenus){
        if (dailyMenus != null) {
            return dailyMenus.stream().map((dm) -> new DailyMenuTo(
                    dm.getId(),
                    dm.getRestaurant().getName(),
                    dm.getDailyDishes().stream().mapToInt(Dish::getCost).sum(),
                    dm.getDailyDishes()
            )).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public static List<VoteResultTo> getVoteResultTo(List<DailyMenu> dailyMenus) {
        if (dailyMenus != null) {
            return dailyMenus.stream().map((dm) -> new VoteResultTo(
                    dm.getRestaurant().getName(),
                    dm.getVotes().size()
            )).collect(Collectors.toList());
        }
        return Collections.emptyList();
    }
}
