package ru.snx.lunchvotes.utils;

import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.utils.exceptions.NotFoundException;
import ru.snx.lunchvotes.utils.exceptions.NotTodayException;
import ru.snx.lunchvotes.utils.exceptions.OutOfTimeException;

import java.time.LocalDate;
import java.time.LocalTime;

public class LimitationChecker {

    public static boolean isValidTime() {
        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            throw new OutOfTimeException("Too late for voting today!");
        }
        return true;
    }

    public static void checkExistAndTodayDailyMenu(DailyMenu dailyMenu) {
        if (notExist(dailyMenu)) throw new NotFoundException(
                "Daily menu not found!"
        );
        if (!dailyMenu.getDate().isEqual(LocalDate.now())) throw new NotTodayException(
                "This is not today's menu!"
        );
    }

    public static void checkExistRestaurant(Restaurant restaurant) {
        if (notExist(restaurant)) throw new NotFoundException(
                "Restaurant not found!"
        );
    }

    private static <T> boolean notExist(T object) {
        return object == null;
    }

}
