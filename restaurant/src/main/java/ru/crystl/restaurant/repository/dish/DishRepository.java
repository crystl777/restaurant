package ru.crystl.restaurant.repository.dish;

import ru.crystl.restaurant.model.Dish;

public interface DishRepository {

    Dish save (Dish dish, int restaurantId);

    boolean delete (int id, int restaurantId);

    Dish get(int id, int restaurantId);
}
