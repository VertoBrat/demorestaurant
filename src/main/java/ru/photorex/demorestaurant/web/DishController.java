package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.excp.DishNotFoundException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.to.DishAssembler;
import ru.photorex.demorestaurant.to.DishResource;

import javax.validation.Valid;

import static ru.photorex.demorestaurant.util.DataValidation.*;


import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/dishes", produces = "application/json")
public class DishController {
    @Autowired
    private RestaurantRepo restaurantRepo;

    @Autowired
    private DishRepo dishRepo;

    @GetMapping
    public Resources<DishResource> all() {
        List<Dish> list = (List<Dish>) dishRepo.findAll();
        List<DishResource> dishes = new DishAssembler().toResources(list);
        return new Resources<>(dishes,
                linkTo(methodOn(DishController.class).all()).withSelfRel());
    }

    @GetMapping("/{id}")
    public Resource<DishResource> one(@PathVariable Long id) {
        Dish dish = dishRepo.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        DishResource resource = new DishAssembler().toResource(dish);
        return new Resource<>(resource,
                linkTo(methodOn(DishController.class).one(id)).withSelfRel());
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<?> add(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody Dish dish, BindingResult result) {
        checkErrors(result);

        Restaurant restaurant =
                restaurantRepo.findById(restaurantId)
                              .orElseThrow(()->new RestaurantNotFoundException(restaurantId));
        dish.setRestaurant(restaurant);
        dishRepo.save(dish);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Dish dish) {
        Dish oldDish = dishRepo.findById(id).orElseThrow(()->new DishNotFoundException(id));
        if (dish.getName()!=null)
            oldDish.setName(dish.getName());
        if (dish.getPrice()!=null)
            oldDish.setPrice(dish.getPrice());
        if (dish.getCreatedAt()!=null)
            oldDish.setCreatedAt(dish.getCreatedAt());
        if (dish.getRestaurant()!=null)
            oldDish.setRestaurant(dish.getRestaurant());

        dishRepo.save(oldDish);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
