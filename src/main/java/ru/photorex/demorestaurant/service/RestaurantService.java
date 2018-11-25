package ru.photorex.demorestaurant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.domain.Vote;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundException;
import ru.photorex.demorestaurant.excp.RestaurantNotFoundNewDishException;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.repo.RestaurantRepo;
import ru.photorex.demorestaurant.repo.VoteRepo;
import ru.photorex.demorestaurant.to.DishAssembler;
import ru.photorex.demorestaurant.to.DishTo;
import ru.photorex.demorestaurant.to.RestaurantAssembler;
import ru.photorex.demorestaurant.to.RestaurantTo;
import ru.photorex.demorestaurant.web.RestaurantController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Service
@Transactional(readOnly = true)
public class RestaurantService {

    private DishRepo dishRepo;
    private RestaurantRepo restaurantRepo;
    private VoteRepo voteRepo;
    @Autowired
    private RestaurantAssembler restaurantAssembler;

    @Autowired
    public RestaurantService(DishRepo dishRepo, RestaurantRepo restaurantRepo, VoteRepo voteRepo) {
        this.dishRepo = dishRepo;
        this.restaurantRepo = restaurantRepo;
        this.voteRepo = voteRepo;
    }

    public Resources<RestaurantTo> getAllCurrentDay(LocalDate currentDay) {
        List<Restaurant> restaurantsRepo =
                restaurantRepo.getByDay(currentDay)
                        .stream()
                        .peek(r -> prepareData(r, currentDay))
                        .collect(Collectors.toList());

        List<RestaurantTo> restaurants =
                new RestaurantAssembler().toResources(restaurantsRepo).stream()
                        //.sorted(Comparator.comparingDouble(RestaurantTo::getVoteRank))
                        .sorted((a, b) -> b.getVoteRank().compareTo(a.getVoteRank()))
                        .collect(Collectors.toList());

        return new Resources<>(restaurants,
                linkTo(methodOn(RestaurantController.class).lastAll(currentDay)).withRel("restaurants"));
    }

    public Resources<RestaurantTo> getAll() {
        List<Restaurant> restaurantsRepo = (List<Restaurant>) restaurantRepo.findAll();
        List<RestaurantTo> restaurants =
                new RestaurantAssembler().toResources(restaurantsRepo);

        return new Resources<>(restaurants,
                linkTo(methodOn(RestaurantController.class).all()).withSelfRel());
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
        RestaurantTo r = new RestaurantAssembler().toResource(restaurant);

        return new Resource<>(r, linkTo(methodOn(RestaurantController.class)
                .getOne(id)).withSelfRel());
    }

    @Transactional
    @CacheEvict(value = {"restaurant", "pagingRest"}, allEntries = true)
    public ResponseEntity<?> create(Restaurant restaurant) {
        if (Objects.isNull(restaurant.getDishes())) {
            restaurant.setDishes(new ArrayList<>());
            restaurantRepo.save(restaurant);
        } else
            restaurantRepo.save(restaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @CacheEvict(value = {"restaurant", "pagingRest"}, allEntries = true)
    public ResponseEntity<?> update(Long id, Restaurant restaurant) {
        Restaurant oldRestaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        if (restaurant.getName() != null)
            oldRestaurant.setName(restaurant.getName());
        if (restaurant.getLocation() != null)
            oldRestaurant.setLocation(restaurant.getLocation());
        if (restaurant.getDishes() != null)
            oldRestaurant.setDishes(restaurant.getDishes());

        restaurantRepo.save(oldRestaurant);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Transactional
    @CacheEvict(value = {"restaurant", "pagingRest"}, allEntries = true)
    public ResponseEntity<?> delete(Long id) {
        Restaurant restaurant = restaurantRepo.findById(id)
                .orElseThrow(() -> new RestaurantNotFoundException(id));
        restaurantRepo.delete(restaurant);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private void prepareData(Restaurant r, LocalDate date) {
        r.setVotes(voteRepo.findByRestaurantAndCreatedAtBetween(r,
                LocalDateTime.of(date, LocalTime.MIDNIGHT),
                LocalDateTime.of(date, LocalTime.MAX)));
    }

    public ResponseEntity<?> getPaging(LocalDate ld, Pageable pageable, PagedResourcesAssembler<Restaurant> assembler) {
        Page<Restaurant> p = restaurantRepo.getPaged(ld, pageable);
        p.forEach(r -> prepareData(r, ld));
        return new ResponseEntity<>(assembler.toResource(p, restaurantAssembler), HttpStatus.OK);
    }
}
