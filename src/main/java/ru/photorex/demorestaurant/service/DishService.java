package ru.photorex.demorestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.excp.DishNotFoundException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.to.DishAssembler;
import ru.photorex.demorestaurant.to.DishTo;
import ru.photorex.demorestaurant.web.DishController;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
@Transactional(readOnly = true)
public class DishService {

    private RestaurantRepo restaurantRepo;
    private DishRepo dishRepo;

    @Autowired
    public DishService(RestaurantRepo restaurantRepo, DishRepo dishRepo) {
        this.restaurantRepo = restaurantRepo;
        this.dishRepo = dishRepo;
    }

    public Resources<DishTo> getAll() {
        List<Dish> list = (List<Dish>) dishRepo.findAll();
        List<DishTo> dishes = new DishAssembler().toResources(list);
        return new Resources<>(dishes,
                linkTo(methodOn(DishController.class).all()).withSelfRel());
    }

    public Resource<DishTo> getById(Long id) {
        Dish dish = dishRepo.findById(id).orElseThrow(() -> new DishNotFoundException(id));
        DishTo resource = new DishAssembler().toResource(dish);
        return new Resource<>(resource,
                linkTo(methodOn(DishController.class).one(id)).withSelfRel());
    }

    @Transactional
    public ResponseEntity<?> create(Long restaurantId, Dish dish) {
        Restaurant restaurant =
                restaurantRepo.findById(restaurantId)
                        .orElseThrow(()->new RestaurantNotFoundException(restaurantId));
        restaurant.init();
        dish.setRestaurant(restaurant);
        dishRepo.save(dish);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<?> update(Long id, Dish dish) {
        Dish oldDish = dishRepo.findById(id).orElseThrow(()->new DishNotFoundException(id));
        boolean isNew = false;
        if (dish.getName()!=null) {
            oldDish.setName(dish.getName());
            isNew = true;
        }
        if (dish.getPrice()!=null) {
            oldDish.setPrice(dish.getPrice());
            isNew = true;
        }
        if (dish.getCreatedAt()!=null) {
            oldDish.setCreatedAt(dish.getCreatedAt());
            isNew = true;
        }
        if (dish.getRestaurant()!=null) {
            oldDish.setRestaurant(dish.getRestaurant());
            isNew = true;
        }

        if (isNew) {
            dishRepo.save(oldDish);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.PRECONDITION_FAILED);
    }

    @Transactional
    public ResponseEntity<?> delete(Long id) {
        Dish dish = dishRepo.findById(id).orElseThrow(()->new DishNotFoundException(id));
        dishRepo.delete(dish);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}