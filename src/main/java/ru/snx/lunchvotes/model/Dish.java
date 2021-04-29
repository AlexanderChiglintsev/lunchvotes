package ru.snx.lunchvotes.model;

import javax.persistence.*;

@Entity
@Table(name = "dishes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"description", "cost", "daily_menu_id"},
                name = "unique_dish_daily_menu_idx")
)
public class Dish {

    public static final int START_SEQ = 500;

    @Id
    @SequenceGenerator(name = "dishes_seq", sequenceName = "dishes_seq",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dishes_seq")
    private int id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "cost", nullable = false)
    private int cost;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_menu_id", nullable = false)
    private DailyMenu dailyMenu;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public DailyMenu getDailyMenu() {
        return dailyMenu;
    }

    public void setDailyMenu(DailyMenu dailyMenu) {
        this.dailyMenu = dailyMenu;
    }
}
