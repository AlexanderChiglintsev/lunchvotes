package ru.snx.lunchvotes.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.snx.lunchvotes.model.DailyMenu;
import ru.snx.lunchvotes.model.Vote;
import ru.snx.lunchvotes.repository.DailyMenuRepository;
import ru.snx.lunchvotes.repository.VoteRepository;
import ru.snx.lunchvotes.to.VoteResultTo;
import ru.snx.lunchvotes.utils.DateContainer;
import ru.snx.lunchvotes.utils.SecurityUtil;

import java.net.URI;
import java.util.List;

import static ru.snx.lunchvotes.utils.LimitationChecker.*;
import static ru.snx.lunchvotes.utils.ToConverter.getVoteResultTo;

@RestController
@RequestMapping(value = "/votes", produces = MediaType.APPLICATION_JSON_VALUE)
public class VoteController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());

    private final VoteRepository voteRepository;
    private final DailyMenuRepository dailyMenuRepository;

    public VoteController(VoteRepository voteRepository, DailyMenuRepository dailyMenuRepository) {
        this.voteRepository = voteRepository;
        this.dailyMenuRepository = dailyMenuRepository;
    }

    @GetMapping("/{id}")
    public Vote get(@PathVariable Integer id) {
        Vote v = voteRepository.getWithUser(id);
        LOG.debug("Get vote with id = {}", id);
        checkOwner(v.getUser(), SecurityUtil.getAuthUser());
        return voteRepository.get(id);
    }

    @PostMapping()
    public ResponseEntity<Vote> save(@RequestParam Integer dmId) {
        isValidTime();
        DailyMenu dailyMenu = dailyMenuRepository.get(dmId);
        checkExistAndTodayDailyMenu(dailyMenu);
        Vote vote = voteRepository.getByDate(DateContainer.getDate(), SecurityUtil.getAuthUser().getEmail());
        if (vote == null) {
            LOG.debug("Save new vote");
            vote = new Vote(null, dailyMenu, DateContainer.getDate(), null);
        } else {
            LOG.debug("Update vote with id = {}", vote.getId());
            vote.setDailyMenu(dailyMenu);
        }
        Vote saved = voteRepository.save(vote, SecurityUtil.getAuthUser().getEmail());

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/votes/{id}")
                .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(saved);
    }

    @GetMapping("/today")
    public List<VoteResultTo> getTodayVotesResult() {
        LOG.debug("Get daily votes results");
        return getVoteResultTo(dailyMenuRepository.getAllWithVotes(DateContainer.getDate()));
    }
}
