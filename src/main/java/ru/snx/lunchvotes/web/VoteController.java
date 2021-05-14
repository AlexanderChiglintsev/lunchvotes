package ru.snx.lunchvotes.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.UserRepository;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.utils.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;

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

    @PostMapping()
    public ResponseEntity<Vote> save(@RequestParam Integer dmId) {
        //isValidTime();
        DailyMenu dailyMenu = dailyMenuRepository.get(dmId);
        //isExistAndToday(dailyMenu);
        Vote v = voteRepository.getByDate(LocalDate.now(), SecurityUtil.getUserId());
        if (v == null) {
            v = new Vote(null, dailyMenu, LocalDate.now(), null);
        } else {
            v.setDailyMenu(dailyMenu);
        }
        Vote saved = voteRepository.save(v, SecurityUtil.getUserId());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(saved);
    }
}
