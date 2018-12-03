package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.web.DishController;
import ru.photorex.demorestaurant.web.RestaurantController;
import ru.photorex.demorestaurant.web.UserController;
import ru.photorex.demorestaurant.web.VoteController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static ru.photorex.demorestaurant.util.AccessUtil.hasAccessToModify;
import static ru.photorex.demorestaurant.util.AccessUtil.hasAccessToVote;

@Component
public class RestaurantProcessor {

    public <T> Resources<T> addLinks(Resources<T> resources) {
        if (hasAccessToModify()) {
            resources.add(linkTo(methodOn(RestaurantController.class).create(null, null)).withRel("add"),
                          linkTo(methodOn(RestaurantController.class).delete(null)).withRel("delete"),
                          linkTo(methodOn(RestaurantController.class).update(null, null)).withRel("update"));
        }

        if (!hasAccessToVote()) {
            resources.add(linkTo(methodOn(UserController.class).create(null, null)).withRel("register-new-user"));
        }

        return resources;
    }

    public Resource<RestaurantTo> addLinks(Resource<RestaurantTo> resource) {
        Long id = resource.getContent().findId();
        if (hasAccessToModify()) {
            resource.add(linkTo(methodOn(DishController.class).add(id, null, null)).withRel("add-dish"),
                    linkTo(methodOn(RestaurantController.class).delete(id)).withRel("delete"),
                    linkTo(methodOn(RestaurantController.class).update(id, null)).withRel("update"));
        }

        return resource;
    }
}
