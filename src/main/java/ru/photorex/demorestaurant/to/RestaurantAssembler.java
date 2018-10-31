package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.web.RestaurantController;

public class RestaurantAssembler extends ResourceAssemblerSupport<Restaurant, RestaurantTo> {

    public RestaurantAssembler() {
        super(RestaurantController.class, RestaurantTo.class);
    }

    @Override
    protected RestaurantTo instantiateResource(Restaurant entity) {
        return new RestaurantTo(entity);
    }

    @Override
    public RestaurantTo toResource(Restaurant restaurant) {
        RestaurantTo resource = createResourceWithId(restaurant.getId(), restaurant);
        resource.add(ControllerLinkBuilder
                    .linkTo(RestaurantController.class).slash(restaurant.getId()).slash("dishes").withRel("lastDishes"));
        return resource;

    }
}
