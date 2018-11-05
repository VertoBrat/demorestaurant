package ru.photorex.demorestaurant.excp;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String userName) {
        super("User with name: " + userName + ", not found");
    }
}
