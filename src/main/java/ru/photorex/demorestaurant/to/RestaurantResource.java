package ru.photorex.demorestaurant.to;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import ru.photorex.demorestaurant.domain.Restaurant;

import java.util.List;

public class RestaurantResource extends ResourceSupport {
    private static final DishAssembler da = new DishAssembler();

    @Getter
    private String name;

    @Getter
    private String location;

    @Getter
    private List<DishResource> dishes;

    public RestaurantResource(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.location = restaurant.getLocation();
        this.dishes = da.toResources(restaurant.getDishes());
    }
}
