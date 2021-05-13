package ru.snx.lunchvotes.utils;

import ru.snx.lunchvotes.model.DailyMenu;

import java.time.LocalDate;
import java.time.LocalTime;

public class LimitationChecker {

    public static boolean isValidTime(){
        if (LocalTime.now().isAfter(LocalTime.of(11, 0))) {
            throw new OutOfTimeException("Too late for voting today!");
        }
        return true;
    }

    public static boolean isExistAndToday(DailyMenu dailyMenu){
        if (dailyMenu == null) throw new NotFoundException(
                "Daily menu with such id not found!"
        );
        if (!dailyMenu.getDate().isEqual(LocalDate.now())) throw new NotTodayException(
                "This is not today's menu!"
        );
        return true;
    }

}
