package ru.photorex.demorestaurant.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.hateoas.Resources;

import java.util.Collections;

import static java.time.LocalDate.now;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping
    public Resources<String> all () {
        return new Resources<String>(Collections.EMPTY_LIST,linkTo(methodOn(RestaurantController.class).lastAll(now())).withRel("restaurants"),
                                                        linkTo(methodOn(DishController.class).all()).withRel("dishes"));
    }
}
