package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.Resources;
import ru.photorex.demorestaurant.repo.UserRepo;

import java.util.Collections;

import static java.time.LocalDate.now;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MainController {

    private RepositoryEntityLinks entityLinks;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    public MainController(RepositoryEntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @GetMapping
    public Resources<String> all (@AuthenticationPrincipal User user) {
        if (user == null) {
            return new Resources<String>(Collections.EMPTY_LIST, linkTo(methodOn(RestaurantController.class).getPaged(now(), null, null)).withRel("actualRestaurantsInfo"),
                    linkTo(methodOn(UserController.class).create(null, null)).withRel("newUser"));
        }
        ru.photorex.demorestaurant.domain.User domainUser = userRepo.getByUserName(user.getUsername()).get();
        if (domainUser.getRoles().contains(ru.photorex.demorestaurant.domain.User.Role.ROLE_ADMIN)) {
            new Resources<String>(Collections.EMPTY_LIST,linkTo(methodOn(RestaurantController.class).lastAll(null)).withRel("restaurants"),
                    linkTo(methodOn(RestaurantController.class).lastAll(now())).withRel("actualRestaurantsInfo"),
                    linkTo(methodOn(DishController.class).all()).withRel("dishes"),
                    linkTo(methodOn(UserController.class).create(null, null)).withRel("newUser"));
        }

        return new Resources<String>(Collections.EMPTY_LIST, linkTo(methodOn(RestaurantController.class).lastAll(now())).withRel("actualRestaurantsInfo"),
                linkTo(methodOn(VoteController.class).add(null, null, null)).withRel("vote"));
    }
}
