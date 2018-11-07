package ru.photorex.demorestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.demorestaurant.domain.Vote;
import ru.photorex.demorestaurant.repo.VoteRepo;

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
}
