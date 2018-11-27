package ru.photorex.demorestaurant.to;


import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import ru.photorex.demorestaurant.domain.Dish;

@Relation(collectionRelation = "dishes")
public class DishTo extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private Long price;

    public DishTo(Dish dish) {
        this.name = dish.getName();
        this.price = dish.getPrice();
    }
}
