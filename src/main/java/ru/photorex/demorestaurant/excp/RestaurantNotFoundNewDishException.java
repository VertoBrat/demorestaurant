package ru.photorex.demorestaurant.excp;

public class RestaurantNotFoundNewDishException extends RuntimeException {

    public RestaurantNotFoundNewDishException() {
        super("No Menu for current day");
    }
}
