package ru.snx.lunchvotes.utils;

import org.springframework.core.NestedExceptionUtils;
import org.springframework.lang.NonNull;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.model.Role;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.utils.exceptions.NotFoundException;
import ru.snx.lunchvotes.utils.exceptions.NotOwnerException;
import ru.snx.lunchvotes.utils.exceptions.NotTodayException;
import ru.snx.lunchvotes.utils.exceptions.OutOfTimeException;

import java.time.LocalTime;
import java.util.Objects;

public class LimitationChecker {

    public static void isValidTime() {
        if (DateContainer.getTime().isAfter(LocalTime.of(11, 0))) {
            throw new OutOfTimeException("Too late for voting today!");
        }
    }

    public static void checkExistAndTodayDailyMenu(DailyMenu dailyMenu) {
        if (notExist(dailyMenu)) throw new NotFoundException(
                "Daily menu not found!"
        );
        if (!dailyMenu.getDate().isEqual(DateContainer.getDate())) throw new NotTodayException(
                "This is not today's menu!"
        );
    }

    public static void checkExistRestaurant(Restaurant restaurant) {
        if (notExist(restaurant)) throw new NotFoundException(
                "Restaurant not found!"
        );
    }

    public static void checkExistUser(User user) {
        if (notExist(user)) throw new NotFoundException(
                "User not found!"
        );
    }

    public static void checkOwner(User u, User authUser) {
        if (!Objects.equals(u.getEmail(), authUser.getEmail()) && !authUser.getRoles().contains(Role.ADMIN)) {
            throw new NotOwnerException("Can't get, not owner!");
        }
    }

    //  https://stackoverflow.com/a/65442410/548473
    @NonNull
    public static Throwable getRootCause(@NonNull Throwable t) {
        Throwable rootCause = NestedExceptionUtils.getRootCause(t);
        return rootCause != null ? rootCause : t;
    }

    private static <T> boolean notExist(T object) {
        return object == null;
    }
}
