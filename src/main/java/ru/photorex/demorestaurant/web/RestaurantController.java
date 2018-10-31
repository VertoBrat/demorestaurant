package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.service.RestaurantService;
import ru.photorex.demorestaurant.to.DishTo;
import ru.photorex.demorestaurant.to.RestaurantTo;
import static ru.photorex.demorestaurant.util.DataValidation.*;

import javax.validation.Valid;
import java.time.LocalDate;



@RestController
@RequestMapping(path = "/api/restaurants", produces = "application/json")
public class RestaurantController {

    private RestaurantService restaurantService;

    @Autowired
    public RestaurantController(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @GetMapping
    public Resources<RestaurantTo> lastAll(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                           @RequestParam(value = "day", required = false)
                                           LocalDate date) {

        LocalDate ld = date!=null ? date:LocalDate.now();
        return restaurantService.getAllCurrentDay(ld);
    }

    @GetMapping("/all")
    public Resources<RestaurantTo> all() {
        return restaurantService.getAll();
    }

    @GetMapping("/{id}/dishes")
    public Resources<DishTo> getDishesPerOneRestaurant(@PathVariable Long id) {
        return restaurantService.getDishesPerOneRestaurant(id);
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
