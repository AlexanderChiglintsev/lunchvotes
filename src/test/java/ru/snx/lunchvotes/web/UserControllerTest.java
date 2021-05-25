package ru.snx.lunchvotes.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.snx.lunchvotes.model.Role;
import ru.snx.lunchvotes.model.User;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.snx.lunchvotes.utils.TestData.*;

class UserControllerTest extends AbstractControllerTest {

    private static final String URL = "/users";

    @Test
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL + "/" + USER_ID)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(user)));
    }

    @Test
    void getAll() throws Exception {
        perform(MockMvcRequestBuilders.get(URL)
                .with(userHttpBasic(admin)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(List.of(user, admin))));
    }

    @Test
    void register() throws Exception {
        perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser))
                .with(userHttpBasic(user)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(newUser)));
    }

    @Test
    void setAdminRules() throws Exception {
        User updated = new User(user);
        updated.getRoles().add(Role.ADMIN);
        perform(MockMvcRequestBuilders.patch(URL + "/" + USER_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(admin)))
                .andExpect(status().isCreated())
                .andDo(print())
                .andExpect(content().json(objectMapper.writeValueAsString(updated)));
    }
}