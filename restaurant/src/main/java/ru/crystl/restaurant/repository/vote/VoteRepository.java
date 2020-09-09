package ru.crystl.restaurant.repository.vote;

import ru.crystl.restaurant.model.Vote;

import java.time.LocalDate;
import java.util.List;

public interface VoteRepository {

    Vote save(int userId, int restaurantId);

    boolean delete(int id);

    Vote get(int id);

    Vote getByUserAndDate(int userId, LocalDate date);

    List<Vote> getByRestaurantAndDate(int restaurantId, LocalDate date);

    List<Vote> getAllByDate(LocalDate date);

    List<Vote> getAll();
}
