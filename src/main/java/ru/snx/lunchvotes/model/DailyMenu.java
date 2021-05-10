package ru.snx.lunchvotes.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "daily_menu")
public class DailyMenu {

    public static final int START_SEQ = 400;

    @Id
    @SequenceGenerator(name = "daily_menu_seq", sequenceName = "daily_menu_seq",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "daily_menu_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Restaurant restaurant;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @OneToMany(
            mappedBy = "dailyMenu",
            fetch = FetchType.EAGER
    )
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Dish> dailyDishes;

    @OneToMany(mappedBy = "dailyMenu", fetch = FetchType.LAZY)
    private List<Vote> votes;

    public DailyMenu() {
    }

    public DailyMenu(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dailyDishes) {
        this(id, restaurant, date, dailyDishes, Collections.emptyList());
    }

    public DailyMenu(Integer id, Restaurant restaurant, LocalDate date, Dish... dishes) {
        this(id, restaurant, date, List.of(dishes), Collections.emptyList());
    }

    public DailyMenu(Integer id, Restaurant restaurant, LocalDate date, List<Dish> dailyDishes, List<Vote> votes) {
        this.id = id;
        this.restaurant = restaurant;
        this.date = date;
        this.dailyDishes = dailyDishes;
        this.votes = votes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
