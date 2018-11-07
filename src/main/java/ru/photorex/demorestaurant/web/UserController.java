package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.photorex.demorestaurant.domain.Registration;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.repo.UserRepo;

@RestController
@RequestMapping("/registration")
public class UserController {

    private UserRepo userRepo;
    private PasswordEncoder encoder;

    @Autowired
    public UserController(UserRepo userRepo, PasswordEncoder encoder) {
        this.userRepo = userRepo;
        this.encoder = encoder;
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody User user) {
        userRepo.save(Registration.toUser(encoder, user));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
