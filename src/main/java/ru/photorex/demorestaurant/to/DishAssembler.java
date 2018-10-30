package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.web.DishController;

public class DishAssembler extends ResourceAssemblerSupport<Dish, DishResource> {

    public DishAssembler() {
        super(DishController.class, DishResource.class);

    }

    @Override
    protected DishResource instantiateResource(Dish entity) {
        return new DishResource(entity);
    }

    @Override
    public DishResource toResource(Dish dish) {
        return createResourceWithId(dish.getId(), dish);
    }
}
