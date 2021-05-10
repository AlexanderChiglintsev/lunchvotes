package ru.snx.lunchvotes.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "restaurants")
public class Restaurant {

    public static final int START_SEQ = 200;

    @Id
    @SequenceGenerator(name = "restaurant_seq", sequenceName = "restaurant_seq",
            allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "restaurant_seq")
    private Integer id;

    @Column(name = "name", nullable = false)
    @NotBlank
    private String name;

    public Restaurant() {
    }

    public Restaurant(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
