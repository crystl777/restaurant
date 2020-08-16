package repository.dish;

import model.Dish;

public interface DishRepository {

    Dish save (Dish dish, int userId);

    boolean delete (int id, int userId);

    Dish get(int id);

}
