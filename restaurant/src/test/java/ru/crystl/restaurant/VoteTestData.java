package ru.crystl.restaurant;

import ru.crystl.restaurant.model.Vote;

import java.time.LocalDate;

import static ru.crystl.restaurant.RestaurantTestData.RESTAURANT_1;
import static ru.crystl.restaurant.UserTestData.USER;
import static ru.crystl.restaurant.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static TestMatcher<Vote> VOTE_MATCHER = TestMatcher.usingEqualsAssertions(Vote.class);

    public static final int VOTE_ID = START_SEQ + 6;

    public static final Vote VOTE_1 = new Vote(VOTE_ID, USER, RESTAURANT_1, LocalDate.of(2020, 01, 30));

    public static Vote getNew() {
        return new Vote(null, USER, RESTAURANT_1);
    }
}
