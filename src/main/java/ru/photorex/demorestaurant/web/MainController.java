package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.Resources;
import ru.photorex.demorestaurant.excp.UserNotFoundException;
import ru.photorex.demorestaurant.repo.UserRepo;

import java.util.Collections;

import static java.time.LocalDate.now;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MainController {

    private UserRepo userRepo;

    @Autowired
    public MainController(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping
    public Resources<String> all(@AuthenticationPrincipal User user) {
        if (user == null) {
            return new Resources<String>(Collections.EMPTY_LIST,
                    linkTo(methodOn(RestaurantController.class).getPaged(now(), null, null)).withRel("actual-restaurants"),
                    linkTo(methodOn(RegistrationController.class).create(null, null)).withRel("register-new-user").withType("POST"));
        }
        ru.photorex.demorestaurant.domain.User domainUser =
                userRepo.getByUserName(user.getUsername())
                        .orElseThrow(() -> new UserNotFoundException(user.getUsername()));

        if (domainUser.getRoles().size() == 2) {
            return new Resources<String>(Collections.EMPTY_LIST,
                    linkTo(methodOn(RestaurantController.class).all(null, null)).withRel("restaurants"),
                    linkTo(methodOn(RestaurantController.class).getPaged(now(), null, null)).withRel("actual-restaurants"),
                    linkTo(methodOn(DishController.class).all(null, null)).withRel("dishes"),
                    linkTo(methodOn(RegistrationController.class).createAdmin(null, null)).withRel("register-new-admin").withType("POST"));
        }

        return new Resources<String>(Collections.EMPTY_LIST,
                linkTo(methodOn(RestaurantController.class).getPaged(now(), null, null)).withRel("actual-restaurants"));

    }
}
