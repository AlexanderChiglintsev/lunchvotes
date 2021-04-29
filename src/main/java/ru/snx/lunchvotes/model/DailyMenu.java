package ru.snx.lunchvotes.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "daily_menu")
public class DailyMenu {

    public static final int START_SEQ = 400;

    @Id
    @SequenceGenerator(name = "daily_menu_seq", sequenceName = "daily_menu_seq",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_menu_seq")
    private int id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @OneToMany(mappedBy = "dailyMenu")
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
