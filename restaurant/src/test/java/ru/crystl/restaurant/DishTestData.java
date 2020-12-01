package ru.crystl.restaurant;

import ru.crystl.restaurant.model.Dish;

import java.time.LocalDate;
import java.util.List;

import static ru.crystl.restaurant.model.AbstractBaseEntity.START_SEQ;
import static ru.crystl.restaurant.RestaurantTestData.*;

public class DishTestData {

    public static final int DISH_ID_1 = START_SEQ + 4;

    public static final int DISH_ID_2 = START_SEQ + 5;

    public static final Dish DISH_1 = new Dish(DISH_ID_1, "Dish 1", 70, LocalDate.now(), RESTAURANT);
    public static final Dish DISH_2 = new Dish(DISH_ID_2, "Dish 2", 50, LocalDate.now(), RESTAURANT);

    public static final List<Dish> DISHES = List.of(DISH_1, DISH_2);
}


