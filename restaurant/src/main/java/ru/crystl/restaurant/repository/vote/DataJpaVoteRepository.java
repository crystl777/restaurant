package ru.crystl.restaurant.repository.vote;

import ru.crystl.restaurant.repository.restaurant.DataJpaRestaurantRepository;
import ru.crystl.restaurant.repository.user.DataJpaUserRepository;
import ru.crystl.restaurant.model.Vote;

import java.time.LocalDate;
import java.util.List;

public class DataJpaVoteRepository implements VoteRepository {
    private final CrudVoteRepository crudRepository;
    private final DataJpaUserRepository userRepository;
    private final DataJpaRestaurantRepository restaurantRepository;

    public DataJpaVoteRepository(CrudVoteRepository crudRepository, DataJpaUserRepository userRepository, DataJpaRestaurantRepository restaurantRepository) {
        this.crudRepository = crudRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
    }


    @Override
    public Vote save(int userId, int restaurantId) {
        Vote vote = new Vote();
        vote.setRestaurant(restaurantRepository.get(restaurantId));
        vote.setUser(userRepository.get(userId));
        return crudRepository.save(vote);
    }

    @Override
    public boolean delete(int id) {
        return crudRepository.delete(id) != 0;
    }

    @Override
    public Vote get(int id) {
        return crudRepository.findById(id).orElse(null);
    }

    @Override
    public Vote getByUserAndDate(int userId, LocalDate date) {
        return crudRepository.findByUserAndDate(userId, date);
    }

    @Override
    public List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date) {
        return crudRepository.findByRestaurantAndDate(restaurantId, date);

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
