package ru.snx.lunchvotes.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Restaurant;
import ru.snx.lunchvotes.model.User;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.utils.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final VoteRepository voteRepository;
    private final DailyMenuRepository dailyMenuRepository;
    private SecurityUtil securityUtil;

    public VoteController(VoteRepository voteRepository, DailyMenuRepository dailyMenuRepository) {
        this.voteRepository = voteRepository;
        this.dailyMenuRepository = dailyMenuRepository;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable Integer id) {
        return voteRepository.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Vote> save(@RequestParam("dmId") Integer dmId) {
        User user = securityUtil.getUser();
        DailyMenu dailyMenu = dailyMenuRepository.get(dmId);
        Vote v = voteRepository.getByDate(LocalDate.now(), user.getId());
        if (v == null) {
            v = new Vote(null, dailyMenu, LocalDate.now(), user);
        } else {
            v.setDailyMenu(dailyMenu);
        }
        voteRepository.save(v);
        Vote saved = voteRepository.save(v);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(saved);
    }
}