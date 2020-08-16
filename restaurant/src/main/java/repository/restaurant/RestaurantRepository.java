package repository.restaurant;

import model.Dish;
import model.Restaurant;

import java.util.List;

public interface RestaurantRepository {

    Restaurant save (Restaurant restaurant, int userId);

    boolean delete (int id, int userId);

    Restaurant get (int id);

    List<Dish> getAllDishes (int id);

}
