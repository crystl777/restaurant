package repository.dish;

import model.Dish;
import org.springframework.stereotype.Repository;
import repository.restaurant.CrudRestaurantRepository;

@Repository
public class DataJpaDishRepository implements DishRepository {
    private final CrudDishRepository crudRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaDishRepository(CrudDishRepository crudRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRepository = crudRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Override
    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudRepository.save(dish);
    }

    @Override
    public boolean delete(int id, int restaurantId) {
        if(get(id, restaurantId) != null) {
            return crudRepository.delete(id) != 0;
        }
        return false;
    }

    @Override
    public Dish get(int id, int restaurantId) {
        return crudRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }
}
