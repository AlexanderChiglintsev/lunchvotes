package ru.snx.lunchvotes.model;

public class Dish {

    private int id;

    private String description;

    private int cost;

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
