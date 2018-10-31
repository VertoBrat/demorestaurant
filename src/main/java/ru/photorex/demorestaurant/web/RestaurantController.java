package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.excp.DataNotValidException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundNewDishException;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.to.DishAssembler;
import ru.photorex.demorestaurant.to.DishTo;
import ru.photorex.demorestaurant.to.RestaurantAssembler;
import ru.photorex.demorestaurant.to.RestaurantTo;


import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static ru.photorex.demorestaurant.util.DataValidation.*;

@RestController
@RequestMapping(path = "/api/restaurants", produces = "application/json")
public class RestaurantController {

    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private DishRepo dishRepo;

    @GetMapping
    public Resources<RestaurantTo> lastAll(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                 @RequestParam(value = "day", required = false) LocalDate date) {
        LocalDate ld = date!=null ? date:LocalDate.now();
        List<Restaurant> restaurantsRepo = //restaurantRepo.find(LocalDate.now());
                restaurantRepo.findByUpdatedAt(ld).stream()
                              .peek(r->{
                                      List<Dish> dishes = dishRepo.findByRestaurantAndCreatedAt(r, ld);
                                      r.setDishes(dishes);
                              })
                              .collect(Collectors.toList());

        List<RestaurantTo> restaurants =
                new RestaurantAssembler().toResources(restaurantsRepo);

        return new Resources<>(restaurants,
                linkTo(methodOn(RestaurantController.class).lastAll(ld)).withSelfRel());
    }

    @GetMapping("/all")
    public Resources<RestaurantTo> all() {

        List<Restaurant> restaurantsRepo = (List<Restaurant>) restaurantRepo.findAll();
        List<RestaurantTo> restaurants =
                new RestaurantAssembler().toResources(restaurantsRepo);

        return new Resources<>(restaurants,
                linkTo(methodOn(RestaurantController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}/dishes")
    public Resources<DishTo> getDishesOneRestaurant(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findByIdAndUpdatedAt(id, LocalDate.now())
                                              .orElseThrow(RestaurantNotFoundNewDishException::new);
        List<Dish> dishes =dishRepo.findByRestaurantAndCreatedAt(restaurant, LocalDate.now());

        List<DishTo> dishTos =
                new DishAssembler().toResources(dishes);

        return new Resources<>(dishTos,
                linkTo(methodOn(RestaurantController.class).getDishesOneRestaurant(id)).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<RestaurantTo> getOne(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
        RestaurantTo r = new RestaurantAssembler().toResource(restaurant);
        return new Resource<>(r, ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(RestaurantController.class).getOne(id)).withSelfRel());
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant restaurant, BindingResult result) {
        checkErrors(result);
        if (Objects.isNull(restaurant.getDishes())) {
            restaurant.setDishes(new ArrayList<>());
            restaurantRepo.save(restaurant);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        restaurantRepo.save(restaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        Restaurant oldRestaurant = restaurantRepo.findById(id)
                                                 .orElseThrow(()->new RestaurantNotFoundException(id));
        if (restaurant.getName()!=null)
            oldRestaurant.setName(restaurant.getName());
        if (restaurant.getLocation()!=null)
            oldRestaurant.setLocation(restaurant.getLocation());
        if (restaurant.getDishes()!=null)
            oldRestaurant.setDishes(restaurant.getDishes());

        restaurantRepo.save(oldRestaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Restaurant restaurant = restaurantRepo.findById(id).orElseThrow(()->new RestaurantNotFoundException(id));
        restaurantRepo.delete(restaurant);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
