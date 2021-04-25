package ru.snx.lunchvotes.model;

import java.time.LocalDate;
import java.util.List;

public class DailyMenu {

    private int id;

    private Restaurant restaurant;

    private LocalDate date;

    private List<Dish> dailyDishes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<Dish> getDailyDishes() {
        return dailyDishes;
    }

    public void setDailyDishes(List<Dish> dailyDishes) {
        this.dailyDishes = dailyDishes;
    }
}
