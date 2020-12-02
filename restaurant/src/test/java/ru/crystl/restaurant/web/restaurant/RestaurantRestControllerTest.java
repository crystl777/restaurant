package ru.crystl.restaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.crystl.restaurant.VoteTestData;
import ru.crystl.restaurant.model.Vote;
import ru.crystl.restaurant.repository.vote.DataJpaVoteRepository;
import ru.crystl.restaurant.web.AbstractControllerTest;
import ru.crystl.restaurant.web.json.JsonUtil;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.crystl.restaurant.RestaurantTestData.*;
import static ru.crystl.restaurant.TestUtil.readFromJson;
import static ru.crystl.restaurant.TestUtil.userHttpBasic;
import static ru.crystl.restaurant.UserTestData.USER;
import static ru.crystl.restaurant.VoteTestData.VOTE_MATCHER;

public class RestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    RestaurantRestController controller;

    @Autowired
    DataJpaVoteRepository voteRepository;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(List.of(RESTAURANT_1)));
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_1));
    }

    @Test
    void vote() throws Exception {
        Vote newVote = VoteTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL + RESTAURANT_1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newVote))
                .with(userHttpBasic(USER)));

        Vote created = readFromJson(action, Vote.class);
        int newId = created.id();
        newVote.setId(newId);
        VOTE_MATCHER.assertMatch(created, newVote);
        VOTE_MATCHER.assertMatch(voteRepository.get(newId, RESTAURANT_1_ID), newVote);
    }
}
