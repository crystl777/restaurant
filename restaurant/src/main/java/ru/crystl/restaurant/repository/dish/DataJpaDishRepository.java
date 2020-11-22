package ru.crystl.restaurant.repository.dish;

import ru.crystl.restaurant.repository.restaurant.CrudRestaurantRepository;
import ru.crystl.restaurant.model.Dish;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaDishRepository {
    private final CrudDishRepository crudRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaDishRepository(CrudDishRepository crudRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRepository = crudRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    public Dish save(Dish dish, int restaurantId) {
        if (!dish.isNew() && get(dish.getId(), restaurantId) == null) {
            return null;
        }
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudRepository.save(dish);
    }

    public boolean delete(int id, int restaurantId) {
        if(get(id, restaurantId) != null) {
            return crudRepository.delete(id) != 0;
        }
        return false;
    }

    public Dish get(int id, int restaurantId) {
        return crudRepository.findById(id)
                .filter(dish -> dish.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    public List<Dish> getAll(int restaurantId, LocalDate date) {
        return crudRepository.findAllByRestaurantIdAndDateOrderByName(restaurantId, date);
    }
}
