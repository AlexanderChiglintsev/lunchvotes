package ru.snx.lunchvotes.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"date", "user_id"},
                name = "date_user_unique_idx")
)
public class Vote {
    public static final int START_SEQ = 300;

    @Id
    @SequenceGenerator(name = "votes_seq", sequenceName = "votes_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "votes_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "daily_menu_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private DailyMenu dailyMenu;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    @JsonIgnore
    private User user;

    public Vote() {
    }

    public Vote(Vote vote) {
        this(vote.id, vote.dailyMenu, vote.date, vote.user);
    }

    public Vote(Integer id, DailyMenu dailyMenu, LocalDate date, User user) {
        this.id = id;
        this.dailyMenu = dailyMenu;
        this.date = date;
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public DailyMenu getDailyMenu() {
        return dailyMenu;
    }

    public void setDailyMenu(DailyMenu dailyMenu) {
        this.dailyMenu = dailyMenu;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
