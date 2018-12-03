package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.PagedResources;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import ru.photorex.demorestaurant.web.RestaurantController;
import ru.photorex.demorestaurant.web.VoteController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RestaurantProcessor {

    public <T> PagedResources<T> addLinks(PagedResources<T> resources) {
        if (resources.getMetadata().getTotalElements() <= 0 && hasAccessToModify()) {
            resources.add(linkTo(methodOn(RestaurantController.class).create(null, null)).withRel("add"));
            return resources;
        }

        if (hasAccessToVote()) {
            resources.add(linkTo(methodOn(VoteController.class).add(null, null, null)).withRel("add-vote"));
        }
        if (hasAccessToModify()) {
            resources.add(linkTo(methodOn(RestaurantController.class).create(null, null)).withRel("add"),
                          linkTo(methodOn(RestaurantController.class).delete(null)).withRel("delete"),
                          linkTo(methodOn(RestaurantController.class).update(null, null)).withRel("update"));
        }

        return resources;
    }

    private boolean hasAccessToVote() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }

    private boolean hasAccessToModify() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
