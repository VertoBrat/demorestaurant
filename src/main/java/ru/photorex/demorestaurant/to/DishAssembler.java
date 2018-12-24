package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.web.DishController;

import static ru.photorex.demorestaurant.util.AccessUtil.hasAccessToModify;

@Component
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
        if (hasAccessToModify()) {
            return createResourceWithId(dish.getId(), dish);
        }
        return new DishTo(dish);
    }
}
