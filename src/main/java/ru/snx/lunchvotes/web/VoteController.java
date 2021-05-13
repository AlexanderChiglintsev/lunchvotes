package ru.snx.lunchvotes.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.UserRepository;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.utils.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;

import static ru.snx.lunchvotes.utils.LimitationChecker.isExistAndToday;
import static ru.snx.lunchvotes.utils.LimitationChecker.isValidTime;

@RestController
@RequestMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final VoteRepository voteRepository;
    private final DailyMenuRepository dailyMenuRepository;
    private final UserRepository userRepository;

    public VoteController(VoteRepository voteRepository, DailyMenuRepository dailyMenuRepository, UserRepository userRepository, SecurityUtil securityUtil) {
        this.voteRepository = voteRepository;
        this.dailyMenuRepository = dailyMenuRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable Integer id) {
        return voteRepository.get(id);
    }

    @GetMapping(value = "/vote/{dmId}")
    public ResponseEntity<Vote> save(@PathVariable Integer dmId) {
        isValidTime();
        DailyMenu dailyMenu = dailyMenuRepository.get(dmId);
        isExistAndToday(dailyMenu);
        User user = userRepository.get(SecurityUtil.getUserId());
        Vote v = voteRepository.getByDate(LocalDate.now(), user.getId());
        if (v == null) {
            v = new Vote(null, dailyMenu, LocalDate.now(), user);
        } else {
            v.setDailyMenu(dailyMenu);
        }
        Vote saved = voteRepository.save(v);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(saved);
    }
}
