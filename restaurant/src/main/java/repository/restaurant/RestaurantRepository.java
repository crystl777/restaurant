package repository.restaurant;

import model.Dish;
import model.Restaurant;

import java.time.LocalDate;
import java.util.List;

public interface RestaurantRepository {

    Restaurant save (Restaurant restaurant, int userId);

    boolean delete (int id);

    Restaurant get (int id);

    List<Restaurant> getAll();

    Restaurant getWithDishes (int id, LocalDate date);

    List<Restaurant> getAllWithDishes(LocalDate date);

}
