package ru.snx.lunchvotes.model;

import javax.persistence.*;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    public static final int START_SEQ = 200;

    @Id
    @SequenceGenerator(name = "restaurant_seq", sequenceName = "restaurant_seq",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
