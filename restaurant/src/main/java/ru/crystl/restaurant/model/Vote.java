package ru.crystl.restaurant.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "votes", uniqueConstraints = {@UniqueConstraint(columnNames = {"date", "user_id"}, name = "date_user_idx")})
public class Vote extends AbstractBaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @NotNull
    private User user;

    @Column(name = "date", nullable = false)
    @NotNull
    private LocalDate localDate;

    public Vote() {
    }

    public Vote(Integer id, Restaurant restaurant, LocalDate localDate) {
        super(id);
        this.restaurant = restaurant;
        this.localDate = localDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public User getUser() {
        return user;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

}
