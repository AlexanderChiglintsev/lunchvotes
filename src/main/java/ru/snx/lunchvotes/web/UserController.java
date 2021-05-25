package ru.snx.lunchvotes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.Role;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.repository.UserRepository;
import ru.snx.lunchvotes.utils.SecurityUtil;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static ru.snx.lunchvotes.utils.LimitationChecker.checkExistUser;
import static ru.snx.lunchvotes.utils.LimitationChecker.checkOwner;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public User get(@PathVariable Integer id) {
        LOG.debug("Get User with id = {}", id);
        User user = userRepository.get(id);
        checkOwner(user, SecurityUtil.getAuthUser());
        return user;
    }

    @GetMapping
    public List<User> getAll() {
        LOG.debug("Get all users");
        return userRepository.getAll();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> register(@Valid @RequestBody User user) {
        LOG.debug("Register new user");
        User created = userRepository.save(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{id}").build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PatchMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> setAdminRules(@PathVariable Integer id) {
        User updated = userRepository.get(id);
        checkExistUser(updated);
        updated.getRoles().add(Role.ADMIN);
        LOG.debug("Add admin rules for user with id = {}", id);
        User newAdmin = userRepository.save(updated);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/users/{id}").build().toUri();
        return ResponseEntity.created(uriOfNewResource).body(newAdmin);
    }
}
