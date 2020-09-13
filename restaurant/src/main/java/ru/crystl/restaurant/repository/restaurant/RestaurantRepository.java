package ru.crystl.restaurant.repository.restaurant;

import ru.crystl.restaurant.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save (Restaurant restaurant);

    boolean delete (int id);

    Restaurant get (int id);

    List<Restaurant> getAll();

    Restaurant getWithDishes (int id, LocalDate date);

    List<Restaurant> getAllWithDishes(LocalDate date);

}
