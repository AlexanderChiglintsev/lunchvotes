package ru.snx.lunchvotes.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.repository.UserRepository;

@Component
public class SecurityUtil {
    private final UserRepository userRepository;

    public SecurityUtil(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(){
        return userRepository.getByEmail("user@user.ru");
    }

}
