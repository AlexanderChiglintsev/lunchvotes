package ru.snx.lunchvotes.web;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.to.VoteResultTo;
import ru.snx.lunchvotes.utils.SecurityUtil;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static ru.snx.lunchvotes.utils.LimitationChecker.*;
import static ru.snx.lunchvotes.utils.ToConverter.getVoteResultTo;

@RestController
@RequestMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {
    private final VoteRepository voteRepository;
    private final DailyMenuRepository dailyMenuRepository;

    public VoteController(VoteRepository voteRepository, DailyMenuRepository dailyMenuRepository) {
        this.voteRepository = voteRepository;
        this.dailyMenuRepository = dailyMenuRepository;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable Integer id) {
        Vote v = voteRepository.getWithUser(id);
        checkVoteOwner(v, SecurityUtil.authUserEmail());
        return voteRepository.get(id);
    }

    @PostMapping()
    public ResponseEntity<Vote> save(@RequestParam Integer dmId) {
        isValidTime();
        DailyMenu dailyMenu = dailyMenuRepository.get(dmId);
        checkExistAndTodayDailyMenu(dailyMenu);
        Vote v = voteRepository.getByDate(LocalDate.now(), SecurityUtil.authUserEmail());
        if (v == null) {
            v = new Vote(null, dailyMenu, LocalDate.now(), null);
        } else {
            v.setDailyMenu(dailyMenu);
        }
        Vote saved = voteRepository.save(v, SecurityUtil.authUserEmail());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(saved);
    }

    @GetMapping("/today")
    public List<VoteResultTo> getTodayVotes() {
        return getVoteResultTo(dailyMenuRepository.getAllWithVotes(LocalDate.now()));
    }
}
