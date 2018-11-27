package ru.photorex.demorestaurant.to;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.domain.Vote;

@Relation(collectionRelation = "restaurants")
public class RestaurantToWithoutDishes extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private String location;

    @Getter
    private Double voteRank;

    public RestaurantToWithoutDishes(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.location = restaurant.getLocation();
        this.voteRank = restaurant.getVotes().stream()
                .mapToInt(Vote::getRang).asDoubleStream()
                .average().orElse(0);
    }
}
