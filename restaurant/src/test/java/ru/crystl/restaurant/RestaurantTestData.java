package ru.crystl.restaurant;

import ru.crystl.restaurant.model.Restaurant;

import static ru.crystl.restaurant.DishTestData.DISHES;
import static ru.crystl.restaurant.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static TestMatcher<Restaurant> RESTAURANT_MATCHER = TestMatcher.usingEqualsAssertions(Restaurant.class);

    public static final int RESTAURANT_NOT_FOUND = 10;

    public static final int RESTAURANT_1_ID = START_SEQ + 2;

    public static final int RESTAURANT_2_ID = START_SEQ + 3;


    public static final Restaurant RESTAURANT_1 = new Restaurant(RESTAURANT_1_ID, "Restaurant 1");

    public static final Restaurant RESTAURANT_2 = new Restaurant(RESTAURANT_2_ID, "Restaurant 2");


    static {
        RESTAURANT_1.setDishes(DISHES);
    }

    public static Restaurant getNew() {
        return new Restaurant(null, "New Restaurant");
    }

    public static Restaurant getUpdated() {
        Restaurant updated = new Restaurant(RESTAURANT_1);
        updated.setName("UpdatedName");
        return updated;
    }
}
