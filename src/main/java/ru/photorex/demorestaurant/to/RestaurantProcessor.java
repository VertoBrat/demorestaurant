package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import ru.photorex.demorestaurant.web.DishController;
import ru.photorex.demorestaurant.web.RestaurantController;
import ru.photorex.demorestaurant.web.RegistrationController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import static ru.photorex.demorestaurant.util.AccessUtil.hasAccessToModify;
import static ru.photorex.demorestaurant.util.AccessUtil.hasAccessToVote;

@Component
public class RestaurantProcessor {

    public <T> Resources<T> addLinks(Resources<T> resources) {
        if (hasAccessToModify()) {
            resources.add(linkTo(methodOn(RestaurantController.class).create(null, null)).withRel("add").withType("POST"),
                    linkTo(methodOn(RestaurantController.class).delete(null)).withRel("delete").withType("DELETE"),
                    linkTo(methodOn(RestaurantController.class).update(null, null)).withRel("update").withType("PATCH"));
        }

        if (!hasAccessToVote()) {
            resources.add(linkTo(methodOn(RegistrationController.class).create(null, null)).withRel("register-new-user").withType("POST"));
        }

        return resources;
    }

    public Resource<RestaurantTo> addLinks(Resource<RestaurantTo> resource) {
        Long id = resource.getContent().findId();
        if (hasAccessToModify()) {
            resource.add(linkTo(methodOn(DishController.class).add(id, null, null)).withRel("add-dish").withType("POST"),
                    linkTo(methodOn(RestaurantController.class).delete(id)).withRel("delete").withType("DELETE"),
                    linkTo(methodOn(RestaurantController.class).update(id, null)).withRel("update").withType("PATCH"));
        }

        return resource;
    }
}
