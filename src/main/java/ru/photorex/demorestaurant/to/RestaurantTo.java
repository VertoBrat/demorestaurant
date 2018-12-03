package ru.photorex.demorestaurant.to;

import lombok.Getter;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.domain.Vote;

import java.util.List;

@Relation(collectionRelation = "restaurants")
public class RestaurantTo extends ResourceSupport {
    private static final DishAssembler da = new DishAssembler();

    @Getter
    private String name;

    @Getter
    private String location;

    @Getter
    private Double voteRank;

    @Getter
    private List<DishTo> dishes;

    private Long id;

    public RestaurantTo(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.location = restaurant.getLocation();
        this.dishes = da.toResources(restaurant.getDishes());
        this.voteRank = restaurant.getVotes().stream()
                .mapToInt(Vote::getRang).asDoubleStream()
                .average().orElse(0);
        this.id = restaurant.getId();
    }

    public Long findId() {
        return id;
    }
}
