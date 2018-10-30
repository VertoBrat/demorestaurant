package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.web.RestaurantController;

public class RestaurantAssembler extends ResourceAssemblerSupport<Restaurant, RestaurantResource> {

    public RestaurantAssembler() {
        super(RestaurantController.class, RestaurantResource.class);
    }

    @Override
    protected RestaurantResource instantiateResource(Restaurant entity) {
        return new RestaurantResource(entity);
    }

    @Override
    public RestaurantResource toResource(Restaurant restaurant) {
        RestaurantResource resource = createResourceWithId(restaurant.getId(), restaurant);
        resource.add(ControllerLinkBuilder
                    .linkTo(RestaurantController.class).slash(restaurant.getId()).slash("dishes").withRel("lastDishes"));
        return resource;

    }
}
