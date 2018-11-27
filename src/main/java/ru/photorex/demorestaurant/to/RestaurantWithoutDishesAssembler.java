package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.web.RestaurantController;

@Component
public class RestaurantWithoutDishesAssembler extends ResourceAssemblerSupport<Restaurant, RestaurantToWithoutDishes> {
    public RestaurantWithoutDishesAssembler() {
        super(RestaurantController.class, RestaurantToWithoutDishes.class);
    }

    @Override
    public RestaurantToWithoutDishes toResource(Restaurant entity) {
        RestaurantToWithoutDishes resource = createResourceWithId(entity.getId(), entity);
        return resource;
    }

    @Override
    protected RestaurantToWithoutDishes instantiateResource(Restaurant entity) {
        return new RestaurantToWithoutDishes(entity);
    }
}
