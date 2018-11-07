package ru.photorex.demorestaurant.util;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.photorex.demorestaurant.domain.User;

import java.util.HashSet;

public class Registration {

    public static User toUser(PasswordEncoder encoder, User user, boolean isAdmin) {
        return isAdmin ? new User(user.getUserName(),
                encoder.encode(user.getPassword()),
                user.getEmail(), user.getRoles()) :
                new User(user.getUserName(),
                        encoder.encode(user.getPassword()),
                        user.getEmail(), new HashSet<>());

    }
}
