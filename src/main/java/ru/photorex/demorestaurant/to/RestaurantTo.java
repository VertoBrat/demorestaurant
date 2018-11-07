package ru.photorex.demorestaurant.to;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.domain.Vote;

import java.util.List;
import java.util.stream.IntStream;

public class RestaurantTo extends ResourceSupport {
    private static final DishAssembler da = new DishAssembler();

    @Getter
    private String name;

    @Getter
    private String location;

    @Getter
    private double voteRank;

    @Getter
    private List<DishTo> dishes;

    public RestaurantTo(Restaurant restaurant) {
        this.name = restaurant.getName();
        this.location = restaurant.getLocation();
        this.dishes = da.toResources(restaurant.getDishes());
        this.voteRank = restaurant.getVotes().stream()
                .mapToInt(Vote::getRang).asDoubleStream()
                .average().orElse(0);

    }
}
