package ru.photorex.demorestaurant.to;

import org.springframework.data.domain.Page;
import org.springframework.hateoas.PagedResources;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.web.VoteController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class RestaurantProcessor {

    public PagedResources<RestaurantTo> addLinks(PagedResources<RestaurantTo> resources) {
        if (resources.getMetadata().getTotalElements() <= 0) return resources;
        if (hasAccessToVote()) {
            resources.add(linkTo(methodOn(VoteController.class).add(null, null, null)).withRel("add-vote"));
        }

        return resources;
    }

    private boolean hasAccessToVote() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return !auth.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ANONYMOUS"));
    }
}
