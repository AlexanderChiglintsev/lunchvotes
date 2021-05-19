package ru.snx.lunchvotes.utils;

import ru.snx.lunchvotes.model.*;

import java.time.LocalDate;
import java.util.List;

public class TestData {

    //Users
    public static final int USER_ID = User.START_SEQ;
    public static final int ADMIN_ID = User.START_SEQ + 1;
    public static final User user = new User(USER_ID, "user", "user@user.ru", "user", Role.USER);
    public static final User admin = new User(ADMIN_ID, "admin", "admin@admin.ru", "admin", Role.ADMIN);
    public static final User newUser = new User(USER_ID + 2, "newUser", "new@user.ru", "newuser", Role.USER);

    //Restaurants
    public static final int R1_ID = Restaurant.START_SEQ;
    public static final int R2_ID = Restaurant.START_SEQ + 1;
    public static final int R3_ID = Restaurant.START_SEQ + 2;
    public static final Restaurant restaurantOne = new Restaurant(R1_ID, "RestaurantOne");
    public static final Restaurant restaurantTwo = new Restaurant(R2_ID, "RestaurantTwo");
    public static final Restaurant restaurantThree = new Restaurant(R3_ID, "RestaurantThree");
    public static final Restaurant restaurantNew = new Restaurant(R3_ID + 1, "RestaurantNew");
    public static final List<Restaurant> restaurants;

    //Dishes
    private static final int DISH_ID = Dish.START_SEQ;
    public static final Dish dish1 = new Dish(DISH_ID, "Meet", 1000);
    public static final Dish dish2 = new Dish(DISH_ID + 1, "Coffee", 250);
    public static final Dish dish3 = new Dish(DISH_ID + 2, "Cherry pie", 400);
    public static final Dish dish4 = new Dish(DISH_ID + 3, "Fish", 1200);
    public static final Dish dish5 = new Dish(DISH_ID + 4, "Black tea", 150);
    public static final Dish dish6 = new Dish(DISH_ID + 5, "Vegetables", 800);
    public static final Dish dish7 = new Dish(DISH_ID + 6, "Juice", 250);
    public static final Dish dish8 = new Dish(DISH_ID + 7, "Pancake", 250);
    public static final Dish dish9 = new Dish(DISH_ID + 8, "Beef", 1400);
    public static final Dish dish10 = new Dish(DISH_ID + 9, "Beer", 400);
    public static final Dish dish11 = new Dish(DISH_ID + 10, "Spaghetti", 900);
    public static final Dish dish12 = new Dish(DISH_ID + 11, "Fresh vegetables", 500);
    public static final Dish dish13 = new Dish(DISH_ID + 12, "Cold tea", 160);
    public static final Dish dish14 = new Dish(DISH_ID + 13, "Chicken", 950);
    public static final Dish dish15 = new Dish(DISH_ID + 14, "Potato", 200);
    public static final Dish dish16 = new Dish(DISH_ID + 15, "White vine", 450);
    public static final Dish dish17 = new Dish(DISH_ID + 16, "Apple", 100);

    //Daily menus
    public static final int DAILY_MENU_ID = DailyMenu.START_SEQ;
    public static final DailyMenu dm1 = new DailyMenu(DAILY_MENU_ID, restaurantOne, LocalDate.of(2021, 5, 1), dish1, dish2, dish3);
    public static final DailyMenu dm2 = new DailyMenu(DAILY_MENU_ID + 1, restaurantTwo, LocalDate.of(2021, 5, 1), dish4, dish5);
    public static final DailyMenu dm3 = new DailyMenu(DAILY_MENU_ID + 2, restaurantThree, LocalDate.of(2021, 5, 1), dish6, dish7, dish8);
    public static final DailyMenu dm4 = new DailyMenu(DAILY_MENU_ID + 3, restaurantOne, LocalDate.of(2021, 5, 2), dish9, dish10);
    public static final DailyMenu dm5 = new DailyMenu(DAILY_MENU_ID + 4, restaurantTwo, LocalDate.of(2021, 5, 2), dish11, dish12, dish13);
    public static final DailyMenu dm6 = new DailyMenu(DAILY_MENU_ID + 5, restaurantThree, LocalDate.of(2021, 5, 2), dish14, dish15, dish16, dish17);
    public static final DailyMenu newDailyMenu = new DailyMenu(DAILY_MENU_ID + 6, restaurantThree, LocalDate.of(2021, 5, 1), dish17);

    //Votes
    public static final int VOTE_ID = Vote.START_SEQ;
    public static final Vote vote1 = new Vote(VOTE_ID, dm1, LocalDate.of(2021, 5, 1), user);
    public static final Vote vote2 = new Vote(VOTE_ID + 1, dm1, LocalDate.of(2021, 5, 1), admin);
    public static final Vote vote3 = new Vote(VOTE_ID + 2, dm5, LocalDate.of(2021, 5, 2), user);
    public static final Vote newVote = new Vote(VOTE_ID + 3, dm6, LocalDate.of(2021, 5, 2), admin);

    public static final List<DailyMenu> dailyMenus;

    static {
        dm1.setVotes(List.of(vote1, vote2));
        dailyMenus = List.of(dm1, dm2, dm3);
        dish17.setDailyMenu(newDailyMenu);
        restaurants = List.of(restaurantOne, restaurantTwo, restaurantThree);
    }
}
