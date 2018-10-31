package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.service.DishService;
import ru.photorex.demorestaurant.to.DishTo;

import javax.validation.Valid;

import static ru.photorex.demorestaurant.util.DataValidation.*;


@RestController
@RequestMapping(path = "/api/dishes", produces = "application/json")
public class DishController {

    @Autowired
    private DishService dishService;

    @GetMapping
    public Resources<DishTo> all() {
        return dishService.getAll();
    }

    @GetMapping("/{id}")
    public Resource<DishTo> one(@PathVariable Long id) {
        return dishService.getById(id);
    }

    @PostMapping("/{restaurantId}")
    public ResponseEntity<?> add(@PathVariable("restaurantId") Long restaurantId, @Valid @RequestBody Dish dish, BindingResult result) {
        checkErrors(result);

        return dishService.create(restaurantId, dish);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Dish dish) {
        return dishService.update(id, dish);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        return dishService.delete(id);
    }
}
