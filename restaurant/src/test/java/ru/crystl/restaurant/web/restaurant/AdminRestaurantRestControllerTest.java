package ru.crystl.restaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.crystl.restaurant.RestaurantTestData;
import ru.crystl.restaurant.model.Restaurant;
import ru.crystl.restaurant.util.exception.NotFoundException;
import ru.crystl.restaurant.web.AbstractControllerTest;
import ru.crystl.restaurant.web.json.JsonUtil;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.crystl.restaurant.RestaurantTestData.*;
import static ru.crystl.restaurant.TestUtil.readFromJson;
import static ru.crystl.restaurant.TestUtil.userHttpBasic;
import static ru.crystl.restaurant.UserTestData.ADMIN;
import static ru.crystl.restaurant.VoteTestData.VOTE_1;
import static ru.crystl.restaurant.VoteTestData.VOTE_MATCHER;

public class AdminRestaurantRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminRestaurantRestController.REST_URL + '/';

    @Autowired
    private AdminRestaurantRestController controller;

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(List.of(RESTAURANT_1)));
    }

    @Test
    void getWithDishes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_1_ID + "?withDishes")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT_1));
    }

    @Test
    void getAllVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "votes?date=2020-01-30")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(List.of(VOTE_1)));
    }

    @Test
    void getAllVotesByDate() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + "votes?date=2020-01-30")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(VOTE_MATCHER.contentJson(List.of(VOTE_1)));
    }

    @Test
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertThrows(NotFoundException.class, () -> controller.get(RESTAURANT_1_ID));
    }

    @Test
    void deleteNotFound() throws Exception {
        perform(MockMvcRequestBuilders.delete(REST_URL + RESTAURANT_NOT_FOUND)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }
    @Test
    void createWithLocation() throws Exception {
        Restaurant newRest = RestaurantTestData.getNew();
        ResultActions action = perform(MockMvcRequestBuilders.post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newRest))
                .with(userHttpBasic(ADMIN)));

        Restaurant created = readFromJson(action, Restaurant.class);
        int newId = created.id();
        newRest.setId(newId);
        RESTAURANT_MATCHER.assertMatch(created, newRest);
        RESTAURANT_MATCHER.assertMatch(controller.get(newId), newRest);
    }

    @Test
    void update() throws Exception {
        Restaurant updated = RestaurantTestData.getUpdated();
        perform(MockMvcRequestBuilders.put(REST_URL + RESTAURANT_1_ID).contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isNoContent());

        RESTAURANT_MATCHER.assertMatch(controller.get(RESTAURANT_1_ID), updated);
    }
}
