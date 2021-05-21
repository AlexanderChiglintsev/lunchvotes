package ru.snx.lunchvotes.web;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.snx.lunchvotes.utils.DateContainer;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.snx.lunchvotes.utils.TestData.*;

class VoteControllerTest extends AbstractControllerTest {

    private final String URL = "/votes";

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
        perform(MockMvcRequestBuilders.get(URL + "/" + VOTE_ID)
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(vote1)));
    }

    @Test
    void save() throws Exception {
        perform(MockMvcRequestBuilders.post(URL)
                .param("dmId","405")
                .with(userHttpBasic(admin)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(newVote)));
    }

    @Test
    void saveNotFound() throws Exception {
        perform(MockMvcRequestBuilders.post(URL)
                .param("dmId","444")
                .with(userHttpBasic(user)))
                .andExpect(status().isNotFound());
    }

    @Test
    void saveTooLate() throws Exception {
        DateContainer.setTime(LocalTime.MAX);
        perform(MockMvcRequestBuilders.post(URL)
                .param("dmId","405")
                .with(userHttpBasic(user)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getTodayVotes() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/today")
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }
}