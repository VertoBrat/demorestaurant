package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.service.DishService;
import ru.photorex.demorestaurant.to.DishAssembler;
import ru.photorex.demorestaurant.to.DishTo;

import javax.validation.Valid;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static ru.photorex.demorestaurant.util.DataValidation.*;


@RestController
@RequestMapping(path = "/api/dishes", produces = "application/json")
public class DishController {

    @Autowired
    private DishService dishService;
    @Autowired
    private DishAssembler dishAssembler;

    @GetMapping
    public ResponseEntity<?> all(Pageable pageable, PagedResourcesAssembler<Dish> assembler) {
        Page<Dish> dishPage = dishService.getAll(pageable);
        PagedResources<DishTo> pagedResources = assembler.toResource(dishPage, dishAssembler);
        if (pagedResources.getMetadata().getTotalElements() > 0) {
            pagedResources.add(linkTo(methodOn(DishController.class).add(null, null, null)).withRel("add"),
                    linkTo(methodOn(DishController.class).delete(null)).withRel("delete"),
                    linkTo(methodOn(DishController.class).update(null, null)).withRel("update"));
        }
        return new ResponseEntity<>(pagedResources, HttpStatus.OK);
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
