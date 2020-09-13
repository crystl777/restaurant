package ru.crystl.restaurant.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import ru.crystl.restaurant.model.Restaurant;
import ru.crystl.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import ru.crystl.restaurant.repository.vote.DataJpaVoteRepository;

import java.time.LocalDate;
import java.util.List;

import static ru.crystl.restaurant.util.ValidationUtil.checkNotFoundWithId;

public class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    DataJpaRestaurantRepository repository;

    @Autowired
    DataJpaVoteRepository voteRepository;

    public Restaurant get(int id) {
        log.info("get {}", id);
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAll() {
        log.info("getAll");
        return repository.getAll();
    }

    public Restaurant getWithDishes(int id, LocalDate date) {
        log.info("get {} with dishes", id);
        return checkNotFoundWithId(repository.getWithDishes(id, date), id);
    }

    public List<Restaurant> getAllWithDishes(LocalDate date) {
        log.info("getAllWithDishes");
        return repository.getAllWithDishes(date);
    }
}
