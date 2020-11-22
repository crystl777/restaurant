package ru.crystl.restaurant.repository.vote;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.crystl.restaurant.model.Vote;
import ru.crystl.restaurant.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class
DataJpaVoteRepository{

    private final CrudVoteRepository crudRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRepository = crudRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    public Vote save(Vote vote, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(), restaurantId) == null) {
            return null;
        } else if (!vote.isNew()) {
            vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        }
        return crudRepository.save(vote);
    }

    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    public Vote get(int id, int restaurantId) {
        return crudRepository.findById(id)
                .filter(vote -> vote.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    public Vote getByUserAndDate(int userId, LocalDate date) {
        return crudRepository.findByUserAndDate(userId, date);
    }

    public List<Vote> getAll() {
        return crudRepository.findAll();
    }

    public List<Vote> getAllByDate(LocalDate date) {
        return crudRepository.findByDate(date);
    }

    public List<Vote> getAllByRestaurantAndDate(int restaurantId, LocalDate date) {
        return crudRepository.findByRestaurantAndDate(restaurantId, date);
    }


}
