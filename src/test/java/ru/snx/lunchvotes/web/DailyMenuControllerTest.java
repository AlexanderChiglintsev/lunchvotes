package ru.snx.lunchvotes.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Dish;
import ru.snx.lunchvotes.utils.DateContainer;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.snx.lunchvotes.utils.TestData.*;
import static ru.snx.lunchvotes.utils.ToConverter.getDailyMenuTo;

class DailyMenuControllerTest extends AbstractControllerTest {

    private final String URL = "/dailyMenus";

    @BeforeEach
    void beforeEach() {
        DateContainer.setDate(LocalDate.of(2021, 5, 2));
        DateContainer.setTime(LocalTime.of(10, 0));
    }

    @AfterEach
    void tearDown() {
        DateContainer.setDate(LocalDate.MIN);
        DateContainer.setTime(LocalTime.MIN);
    }

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + DAILY_MENU_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(dm1)));
    }

    @Test
    void getTodayMenus() throws Exception {
        perform(MockMvcRequestBuilders.get(URL)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(getDailyMenuTo(dailyMenus))));
    }

    @Test
    void createDailyMenu() throws Exception {
        DateContainer.setDate(LocalDate.MIN);
        perform(MockMvcRequestBuilders.post(URL)
                .param("restaurantId", "201")
                .with(userHttpBasic(admin)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(todayDailyMenu)));
    }

    @Test
    void createDailyMenuInvalidRestId() throws Exception {
        DateContainer.setDate(LocalDate.MIN);
        perform(MockMvcRequestBuilders.post(URL)
                .param("restaurantId", "777")
                .with(userHttpBasic(admin)))
                .andExpect(status().isNotFound());
    }

    @Test
    void addDish() throws Exception {
        DailyMenu changed = new DailyMenu(dm6);
        changed.addDish(newDish);
        perform(MockMvcRequestBuilders.patch(URL + "/" + dm6.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newDish))
                .with(userHttpBasic(admin)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(changed)));
    }

    @Test
    void addInvalidDish() throws Exception {
        Dish invalidDish = new Dish(null, "", null);
        perform(MockMvcRequestBuilders.patch(URL + "/" + dm6.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidDish))
                .with(userHttpBasic(admin)))
                .andExpect(status().isUnprocessableEntity());
    }
}