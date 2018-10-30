package ru.photorex.demorestaurant.to;


import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import ru.photorex.demorestaurant.domain.Dish;


public class DishResource extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private Long price;

    public DishResource(Dish dish) {
        this.name = dish.getName();
        this.price = dish.getPrice();
    }
}
