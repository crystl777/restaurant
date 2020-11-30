package ru.crystl.restaurant.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.crystl.restaurant.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static ru.crystl.restaurant.RestaurantTestData.*;

public class AdminRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminRestaurantRestController.REST_URL + '/';

    @Autowired
    private AdminRestaurantRestController controller;

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT_ID))
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(RESTAURANT_MATCHER.contentJson(RESTAURANT))
                .andDo(print());
    }
}
