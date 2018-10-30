package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.excp.DataNotValidException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.to.DishAssembler;
import ru.photorex.demorestaurant.to.DishResource;
import ru.photorex.demorestaurant.to.RestaurantAssembler;
import ru.photorex.demorestaurant.to.RestaurantResource;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/restaurants", produces = "application/json")
public class RestaurantController {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private DishRepo dishRepo;

    @GetMapping
    public Resources<RestaurantResource> lastAll() {



        List<Restaurant> restaurants =// restaurantRepo.find(LocalDate.now());

                restaurantRepo.findByUpdatedAt(LocalDate.now()).stream()
                        .peek(r->{
                             List<Dish> dishes = dishRepo.findByRestaurantAndCreatedAt(r, LocalDate.now());
                             r.setDishes(dishes);
                             })
                        .collect(Collectors.toList());



        List<RestaurantResource> restaurantResources =
                new RestaurantAssembler().toResources(restaurants);

        return new Resources<>(restaurantResources,
                linkTo(methodOn(RestaurantController.class).lastAll()).withSelfRel());
    }

    @GetMapping("/all")
    public Resources<RestaurantResource> all() {

        List<Restaurant> restaurants = (List<Restaurant>) restaurantRepo.findAll();
        List<RestaurantResource> restaurantResources =
                new RestaurantAssembler().toResources(restaurants);


        return new Resources<>(restaurantResources,
                linkTo(methodOn(RestaurantController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}/dishes")
    public Resources<DishResource> getDishesOneRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findByIdAndUpdatedAt(id, LocalDate.now());
        List<Dish> dishes =dishRepo.findByRestaurantAndCreatedAt(restaurant, LocalDate.now());

        List<DishResource> dishResources =
                new DishAssembler().toResources(dishes);

        return new Resources<>(dishResources,
                linkTo(methodOn(RestaurantController.class).getDishesOneRestaurant(id)).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<RestaurantResource> getOne(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
        RestaurantResource r = new RestaurantAssembler().toResource(restaurant);
        return new Resource<>(r, ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(RestaurantController.class).getOne(id)).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant restaurant, BindingResult result) {
        if (result.hasErrors()) {
            List<String> errorFields =
                    result.getFieldErrors().stream()
                            .map(r-> String.valueOf(r.getField()))
                            .collect(Collectors.toList());
            throw new DataNotValidException(errorFields);
        }
        restaurantRepo.save(restaurant);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
