package ru.snx.lunchvotes.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.snx.lunchvotes.utils.TestData.*;

class RestaurantControllerTest extends AbstractControllerTest {

    public static final String URL = "/restaurants";

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + R1_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(restaurantOne)));
    }

    @Test
    void getAll() {
    }

    @Test
    void save() throws Exception {

    }
}