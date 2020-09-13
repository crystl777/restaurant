package ru.crystl.restaurant.repository.dish;

import ru.crystl.restaurant.model.Dish;

import java.time.LocalDate;
import java.util.List;

public interface DishRepository {

    Dish save (Dish dish, int restaurantId);

    boolean delete (int id, int restaurantId);

    Dish get(int id, int restaurantId);

    List<Dish> getAll (int restaurantId, LocalDate date);


}
