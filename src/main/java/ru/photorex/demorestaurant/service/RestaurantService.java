package ru.photorex.demorestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;

import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundNewDishException;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.repo.VoteRepo;
import ru.photorex.demorestaurant.to.*;
import ru.photorex.demorestaurant.web.RestaurantController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;


import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private DishRepo dishRepo;
    private RestaurantRepo restaurantRepo;
    private VoteRepo voteRepo;
    private RestaurantAssembler restaurantAssembler;

    @Autowired
    public RestaurantService(DishRepo dishRepo, RestaurantRepo restaurantRepo, VoteRepo voteRepo, RestaurantAssembler restaurantAssembler) {
        this.dishRepo = dishRepo;
        this.restaurantRepo = restaurantRepo;
        this.voteRepo = voteRepo;
        this.restaurantAssembler = restaurantAssembler;
    }

    public Resources<DishTo> getLastDishesPerOneRestaurant(Long id) {
        Restaurant restaurant = restaurantRepo.findByIdAndUpdatedAt(id, LocalDate.now())
                .orElseThrow(RestaurantNotFoundNewDishException::new);

        List<Dish> dishes = dishRepo.findByRestaurantAndCreatedAt(restaurant, LocalDate.now());
        List<DishTo> dishTos = new DishAssembler().toResources(dishes);

        return new Resources<>(dishTos, linkTo(methodOn(RestaurantController.class)
                .getDishesPerOneRestaurant(id)).withSelfRel());
    }

    public Resource<RestaurantTo> getOne(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        RestaurantTo r = restaurantAssembler.toResource(restaurant);

        return new Resource<>(r, linkTo(methodOn(RestaurantController.class)
                .getOne(id)).withSelfRel());
    }

    @Transactional
    @CacheEvict(value = "pagingRest", allEntries = true)
    public ResponseEntity<?> create(Restaurant restaurant) {
        if (Objects.isNull(restaurant.getDishes())) {
            restaurant.setDishes(new ArrayList<>());
            restaurantRepo.save(restaurant);
        } else
            restaurantRepo.save(restaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @CacheEvict(value = "pagingRest", allEntries = true)
    public ResponseEntity<?> update(Long id, Restaurant restaurant) {
        Restaurant oldRestaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        if (restaurant.getName() != null)
            oldRestaurant.setName(restaurant.getName());
        if (restaurant.getLocation() != null)
            oldRestaurant.setLocation(restaurant.getLocation());
        if (restaurant.getDishes() != null)
            oldRestaurant.setDishes(restaurant.getDishes());

       // restaurantRepo.save(oldRestaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @CacheEvict(value = "pagingRest", allEntries = true)
    public ResponseEntity<?> delete(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurantRepo.delete(restaurant);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void prepareData(Restaurant r, LocalDate date) {
        if (date != null) {
            r.setVotes(voteRepo.findByRestaurantAndCreatedAtBetween(r,
                    LocalDateTime.of(date, LocalTime.MIDNIGHT),
                    LocalDateTime.of(date, LocalTime.MAX)));
            return;
        }
        r.setVotes(voteRepo.findAllByRestaurant(r));
    }

    public Page<Restaurant> getPaging(LocalDate ld, Pageable pageable) {
        Page<Restaurant> pagedRestaurants = restaurantRepo.getPaged(ld, pageable);
        pagedRestaurants.forEach(r -> prepareData(r, ld));
        return pagedRestaurants;
    }

    public Page<Restaurant> getAll(Pageable pageable) {
        Page<Restaurant> allPagedRestaurants = restaurantRepo.findAll(pageable);
        allPagedRestaurants.forEach(r -> prepareData(r, null));
        return allPagedRestaurants;
    }
}
