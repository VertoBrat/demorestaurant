package ru.photorex.demorestaurant.excp;

public class RestaurantNotFoundException extends RuntimeException {
    
    public RestaurantNotFoundException(Long id) {
        super("Not found restaurant with id = " + id);
    }
}
