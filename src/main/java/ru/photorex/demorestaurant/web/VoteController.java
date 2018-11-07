package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.photorex.demorestaurant.domain.Restaurant;
import org.springframework.security.core.userdetails.User;
import ru.photorex.demorestaurant.domain.Vote;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.repo.UserRepo;
import ru.photorex.demorestaurant.service.VoteService;

@RestController
@RequestMapping("/vote")
public class VoteController {

    private VoteService voteService;
    private RestaurantRepo restaurantRepo;
    private UserRepo userRepo;

    @Autowired
    public VoteController(VoteService voteService, RestaurantRepo restaurantRepo, UserRepo userRepo) {
        this.voteService = voteService;
        this.restaurantRepo = restaurantRepo;
        this.userRepo = userRepo;
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<?> add(@PathVariable long restaurantId,
                                 @RequestParam(value = "rank",
                                         defaultValue = "0",
                                         required = false) int rank,
                                 @AuthenticationPrincipal User user) {

        Restaurant restaurant = restaurantRepo.getOne(restaurantId);
        if (restaurant == null)
            throw new RestaurantNotFoundException(restaurantId);
        ru.photorex.demorestaurant.domain.User user1 = userRepo.getByUserName(user.getUsername()).get();
        Vote vote = new Vote();
        vote.setRang(rank);
        vote.setRestaurant(restaurant);
        vote.setUser(user1);

        return voteService.save(vote);
    }
}
