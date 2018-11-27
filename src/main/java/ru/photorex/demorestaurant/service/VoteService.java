package ru.photorex.demorestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.domain.Vote;
import ru.photorex.demorestaurant.excp.TooLateAddVoteException;
import ru.photorex.demorestaurant.repo.VoteRepo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@Transactional(readOnly = true)
public class VoteService {

    private VoteRepo voteRepo;

    @Autowired
    public VoteService(VoteRepo voteRepo) {
        this.voteRepo = voteRepo;
    }

    @Transactional
    public ResponseEntity<?> save(Vote vote) {
        voteRepo.save(vote);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private Vote getByUserId(long userId) {
        LocalDateTime ldt = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        return voteRepo.findByUserIdAndCreatedAtBetween(userId, ldt, LocalDateTime.now());
    }

    @Transactional
    public ResponseEntity<?> add(Restaurant restaurant, User user, int rank) {
        LocalDateTime now = LocalDateTime.now();
        Vote v = getByUserId(user.getId());
        LocalTime lt = LocalTime.of(11, 0);
        if (v != null && lt.isBefore(now.toLocalTime()))
            throw new TooLateAddVoteException();
        if (v == null) {
            Vote vote = new Vote();
            vote.setRang(rank);
            vote.setRestaurant(restaurant);
            vote.setUser(user);

            return save(vote);
        } else {
            v.setRang(rank);
            v.setRestaurant(restaurant);
            v.setCreatedAt(now);

            return save(v);
        }
    }
}
