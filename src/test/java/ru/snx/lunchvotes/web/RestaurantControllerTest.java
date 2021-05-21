package ru.snx.lunchvotes.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.snx.lunchvotes.model.Restaurant;

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
    void getForbidden() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + R1_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isForbidden());
    }

    @Test
    void getUnauthorized() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + R1_ID)
                .with(userHttpBasic(newUser)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(restaurants)));
    }

    @Test
    void save() throws Exception {
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(restaurantNew))
                .with(userHttpBasic(admin)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(restaurantNew)));
    }

    @Test
    @Transactional(propagation = Propagation.NEVER)
    void saveWithExistingName() throws Exception {
        Restaurant saved = new Restaurant(restaurantOne);
        saved.setId(null);
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(saved))
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }
}