package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.service.RestaurantService;
import ru.photorex.demorestaurant.to.*;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static ru.photorex.demorestaurant.util.DataValidation.*;

import javax.validation.Valid;
import java.time.LocalDate;


@RestController
@RequestMapping(path = "/api/restaurants", produces = "application/hal+json")
public class RestaurantController {

    private RestaurantService restaurantService;
    private RestaurantAssembler restaurantAssembler;
    private RestaurantWithoutDishesAssembler restaurantWithoutDishesAssembler;
    private RestaurantProcessor restaurantProcessor;

    @Autowired
    public RestaurantController(RestaurantService restaurantService,
                                RestaurantAssembler restaurantAssembler,
                                RestaurantWithoutDishesAssembler restaurantWithoutDishesAssembler,
                                RestaurantProcessor restaurantProcessor) {

        this.restaurantService = restaurantService;
        this.restaurantAssembler = restaurantAssembler;
        this.restaurantWithoutDishesAssembler = restaurantWithoutDishesAssembler;
        this.restaurantProcessor = restaurantProcessor;
    }

    @GetMapping
    public ResponseEntity<?> getPaged(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                      @RequestParam(value = "day", required = false)
                                      LocalDate date,
                                      Pageable pageable,
                                      PagedResourcesAssembler<Restaurant> assembler) {

        LocalDate ld = date != null ? date : LocalDate.now();
        Page<Restaurant> paging = restaurantService.getPaging(ld, pageable);
        PagedResources<RestaurantTo> restaurantTos = assembler
                .toResource(paging, restaurantAssembler);
        restaurantProcessor.addLinks(restaurantTos);
        return new ResponseEntity<>(restaurantTos, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?> all(Pageable pageable, PagedResourcesAssembler<Restaurant> assembler) {
        PagedResources<RestaurantToWithoutDishes> restaurantToWithoutDishes =
                assembler.toResource(restaurantService.getAll(pageable), restaurantWithoutDishesAssembler);
        restaurantProcessor.addLinks(restaurantToWithoutDishes);
        return new ResponseEntity<>(restaurantToWithoutDishes, HttpStatus.OK);
    }

    @GetMapping("/{id}/dishes")
    public Resources<DishTo> getDishesPerOneRestaurant(@PathVariable Long id) {
        return restaurantService.getLastDishesPerOneRestaurant(id);
    }

    @GetMapping("/{id}")
    public Resource<RestaurantTo> getOne(@PathVariable Long id) {
        Resource<RestaurantTo> restaurant = restaurantService.getOne(id);
        restaurantProcessor.addLinks(restaurant);
        return restaurant;
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody Restaurant restaurant, BindingResult result) {
        checkErrors(result);
        return restaurantService.create(restaurant);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Restaurant restaurant) {
        return restaurantService.update(id, restaurant);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return restaurantService.delete(id);
    }
}
