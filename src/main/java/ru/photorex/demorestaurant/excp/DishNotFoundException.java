package ru.photorex.demorestaurant.excp;

public class DishNotFoundException extends RuntimeException {
    public DishNotFoundException(Long id) {
        super("Not found dish with id = " + id);
    }
}
