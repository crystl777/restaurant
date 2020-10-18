package ru.crystl.restaurant.repository.vote;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.crystl.restaurant.model.Vote;
import ru.crystl.restaurant.repository.restaurant.CrudRestaurantRepository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class
DataJpaVoteRepository implements VoteRepository {

    private final CrudVoteRepository crudRepository;
    private final CrudRestaurantRepository crudRestaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudRepository, CrudRestaurantRepository crudRestaurantRepository) {
        this.crudRepository = crudRepository;
        this.crudRestaurantRepository = crudRestaurantRepository;
    }

    @Transactional
    @Override
    public Vote save(Vote vote, int restaurantId) {
        if (!vote.isNew() && get(vote.getId(), restaurantId) == null) {
            return null;
        } else if (!vote.isNew()) {
            vote.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        }
        return crudRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id, int restaurantId) {
        return crudRepository.findById(id)
                .filter(vote -> vote.getRestaurant().getId() == restaurantId)
                .orElse(null);
    }

    @Override
    public Vote getByUserAndDate(int userId, LocalDate date) {
        return crudRepository.findByUserAndDate(userId, date);

    }

    @Override
    public List<Vote> getAllByDate(LocalDate date) {
        return crudRepository.findByDate(date);
    }

    @Override
    public List<Vote> getAll() {
        return crudRepository.findAll();
    }


}
