package ru.crystl.restaurant;

import ru.crystl.restaurant.model.Restaurant;

import static ru.crystl.restaurant.DishTestData.DISHES;
import static ru.crystl.restaurant.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {

    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingEqualsAssertions(Restaurant.class);

    public static final int RESTAURANT_ID = START_SEQ + 2;

    public static final Restaurant RESTAURANT = new Restaurant(RESTAURANT_ID, "Restaurant 1");

    static {
        RESTAURANT.setDishes(DISHES);
    }
}
