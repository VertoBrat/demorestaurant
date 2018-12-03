package ru.photorex.demorestaurant.to;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;
import static ru.photorex.demorestaurant.util.AccessUtil.hasAccessToModify;
import static ru.photorex.demorestaurant.util.AccessUtil.hasAccessToVote;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.core.Relation;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.web.RestaurantController;
import ru.photorex.demorestaurant.web.VoteController;

@Component
@Relation(collectionRelation = "restaurants")
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

        if (hasAccessToVote()) {
            resource.add(linkTo(methodOn(VoteController.class).add(restaurant.getId(),null, null)).withRel("add-vote"));
        }

        if (hasAccessToModify()) {
            resource.add(linkTo(methodOn(RestaurantController.class).getDishesPerOneRestaurant(restaurant.getId())).withRel("last-dishes-per-restaurant"));
        }
        return resource;
    }
}
