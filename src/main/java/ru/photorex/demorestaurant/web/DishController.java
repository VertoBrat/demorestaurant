package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.excp.DishNotFoundException;
import ru.photorex.demorestaurant.repo.DishRepo;
import ru.photorex.demorestaurant.to.DishAssembler;
import ru.photorex.demorestaurant.to.DishResource;


import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "/api/dishes", produces = "application/json")
public class DishController {

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
}
