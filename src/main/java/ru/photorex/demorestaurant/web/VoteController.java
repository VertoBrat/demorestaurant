package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import ru.photorex.demorestaurant.domain.Restaurant;
import org.springframework.security.core.userdetails.User;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundNewDishException;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.repo.UserRepo;
import ru.photorex.demorestaurant.service.VoteService;

import java.time.LocalDate;


@RestController
@RequestMapping("/api/votes")
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
    public ResponseEntity<?> add(@PathVariable Long restaurantId,
                                 @RequestParam(value = "rank",
                                         defaultValue = "0",
                                         required = false) Integer rank,
                                 @AuthenticationPrincipal User user) {

        Restaurant restaurant = restaurantRepo.findById(restaurantId)
                .orElseThrow(() -> new RestaurantNotFoundException(restaurantId));
        if (restaurant.getUpdatedAt().isBefore(LocalDate.now()))
            throw new RestaurantNotFoundNewDishException();
        ru.photorex.demorestaurant.domain.User domainUser = userRepo.getByUserName(user.getUsername()).get();

        return voteService.add(restaurant, domainUser, rank);

    }
}
