package ru.crystl.restaurant.repository.vote;

import ru.crystl.restaurant.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer>{
    @Modifying
    @Transactional
    @Query("DELETE FROM Vote v WHERE v.id=:id")
    int delete(@Param("id") int id);

    List<Vote> findByDate(LocalDate date);

    Vote findByUserAndDate(int userId, LocalDate date);

    List<Vote> findByRestaurantAndDate(int restaurantId, LocalDate date);
}
