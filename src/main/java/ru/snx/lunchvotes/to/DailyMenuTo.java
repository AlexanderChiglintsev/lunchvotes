package ru.snx.lunchvotes.to;

import ru.snx.lunchvotes.model.Dish;

import java.util.List;

public class DailyMenuTo {

    private final Integer id;

    private final String restaurant;

    private final Integer countVotes;

    private final Integer fullPrice;

    private final List<Dish> dishes;

    public DailyMenuTo(Integer id, String restaurant, Integer countVotes, Integer fullPrice, List<Dish> dishes) {
        this.id = id;
        this.restaurant = restaurant;
        this.countVotes = countVotes;
        this.fullPrice = fullPrice;
        this.dishes = dishes;
    }

    public Integer getId() {
        return id;
    }

    public String getRestaurant() {
        return restaurant;
    }

    public Integer getCountVotes() {
        return countVotes;
    }

    public Integer getFullPrice() {
        return fullPrice;
    }

    public List<Dish> getDishes() {
        return dishes;
    }
}
