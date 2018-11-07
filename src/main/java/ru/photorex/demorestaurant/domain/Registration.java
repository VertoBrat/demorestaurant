package ru.photorex.demorestaurant.domain;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;

@Data
public class Registration {

    public static User toUser(PasswordEncoder encoder, User user) {
        return new User(user.getUserName(),
                encoder.encode(user.getPassword()),
                user.getEmail(), new HashSet<>());
    }
}
