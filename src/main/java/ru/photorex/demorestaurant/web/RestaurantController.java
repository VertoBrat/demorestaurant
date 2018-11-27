package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.service.RestaurantService;
import ru.photorex.demorestaurant.to.DishTo;
import ru.photorex.demorestaurant.to.RestaurantAssembler;
import ru.photorex.demorestaurant.to.RestaurantTo;
import ru.photorex.demorestaurant.to.RestaurantWithoutDishesAssembler;

import static ru.photorex.demorestaurant.util.DataValidation.*;

import javax.validation.Valid;
import java.time.LocalDate;


@RestController
@RequestMapping(path = "/api/restaurants", produces = "application/json")
public class RestaurantController {

    private RestaurantService restaurantService;
    @Autowired
    private RestaurantAssembler restaurantAssembler;
    @Autowired
    private RestaurantWithoutDishesAssembler restaurantWithoutDishesAssembler;


    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    /*@GetMapping
    public Resources<RestaurantTo> lastAll(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           @RequestParam(value = "day", required = false)
                                           LocalDate date) {

        LocalDate ld = date != null ? date : LocalDate.now();
        return restaurantService.getAllCurrentDay(ld);
    }*/

    @GetMapping
    public ResponseEntity<?> getPaged(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                      @RequestParam(value = "day", required = false)
                                      LocalDate date,
                                      Pageable pageable,
                                      PagedResourcesAssembler<Restaurant> assembler) {

        LocalDate ld = date != null ? date : LocalDate.now();

        return new ResponseEntity<>(assembler.toResource(restaurantService.getPaging(ld, pageable), restaurantAssembler), HttpStatus.OK);
    }

   /* @GetMapping("/all")
    public Resources<RestaurantTo> all() {
        return restaurantService.getAll();
    }*/

    @GetMapping("/all")
    public ResponseEntity<?> all(Pageable pageable, PagedResourcesAssembler<Restaurant> assembler) {
        return new ResponseEntity<>(assembler.toResource(restaurantService.getAll(pageable),restaurantWithoutDishesAssembler), HttpStatus.OK);
    }

    @GetMapping("/{id}/dishes")
    public Resources<DishTo> getDishesPerOneRestaurant(@PathVariable Long id) {
        return restaurantService.getLastDishesPerOneRestaurant(id);
    }

    @GetMapping("/{id}")
    public Resource<RestaurantTo> getOne(@PathVariable Long id) {
        return restaurantService.getOne(id);
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
