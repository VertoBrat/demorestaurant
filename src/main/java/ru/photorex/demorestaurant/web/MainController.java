package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.Resources;

import java.util.Collections;

import static java.time.LocalDate.now;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "/api", produces = "application/hal+json")
public class MainController {

    private RepositoryEntityLinks entityLinks;

    @Autowired
    public MainController(RepositoryEntityLinks entityLinks) {
        this.entityLinks = entityLinks;
    }

    @GetMapping
    public Resources<String> all () {
        return new Resources<String>(Collections.EMPTY_LIST,linkTo(methodOn(RestaurantController.class).lastAll(null)).withRel("restaurants"),
                                                        linkTo(methodOn(RestaurantController.class).lastAll(now())).withRel("actualRestaurantsInfo"),
                                                        linkTo(methodOn(DishController.class).all()).withRel("dishes"),
                linkTo(methodOn(UserController.class).create(null, null)).withRel("newUser"));
    }
}
