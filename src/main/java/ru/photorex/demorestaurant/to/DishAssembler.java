package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.web.DishController;

public class DishAssembler extends ResourceAssemblerSupport<Dish, DishTo> {

    public DishAssembler() {
        super(DishController.class, DishTo.class);

    }

    @Override
    protected DishTo instantiateResource(Dish entity) {
        return new DishTo(entity);
    }

    @Override
    public DishTo toResource(Dish dish) {
        return createResourceWithId(dish.getId(), dish);
    }
}
