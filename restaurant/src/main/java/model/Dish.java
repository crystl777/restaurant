package model;

import java.time.LocalDate;

public class Dish extends AbstractNamedEntity {

    private int price;

    private LocalDate localDate;

    private Restaurant restaurant;

    public Dish(int price, LocalDate localDate, Restaurant restaurant) {
        this.price = price;
        this.restaurant = restaurant;
        this.localDate = localDate;


    }


    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
