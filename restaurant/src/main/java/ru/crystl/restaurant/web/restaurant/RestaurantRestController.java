package ru.crystl.restaurant.web.restaurant;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.crystl.restaurant.model.Restaurant;
import ru.crystl.restaurant.model.Vote;
import ru.crystl.restaurant.util.exception.LateVoteException;
import ru.crystl.restaurant.util.exception.NotFoundException;
import ru.crystl.restaurant.web.user.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;

@RestController
@RequestMapping(RestaurantRestController.REST_URL)
public class RestaurantRestController extends AbstractRestaurantController {

    static final String REST_URL = "/rest/profile/restaurants";
    private static final LocalTime VOTE_MAX_TIME_ALLOWED = LocalTime.of(11, 0);

    @GetMapping("/{id}/vote")
    public Vote vote(@PathVariable int id) throws Exception {
        Vote vote = voteRepository.getByUserAndDate(SecurityUtil.authUserId(), LocalDate.now());
        Restaurant restaurant = super.get(id);
        if (restaurant == null) {
            throw new NotFoundException("There is no such restaurant");
        }
        if (vote == null) {
            vote = voteRepository.save(SecurityUtil.authUserId(), id);
        } else {
            if (LocalTime.now().isAfter(VOTE_MAX_TIME_ALLOWED)) {
                throw new LateVoteException("You are not allowed to change your vote after 11:00 A.M.");
            }
            vote.setRestaurant(super.get(id));
        }
        return vote;
    }
}
