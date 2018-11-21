package ru.photorex.demorestaurant.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.photorex.demorestaurant.util.Registration;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.repo.UserRepo;

import static ru.photorex.demorestaurant.util.DataValidation.*;

import javax.validation.Valid;

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
    public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {
        checkErrors(result);
        userRepo.save(Registration.toUser(encoder, user, false));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/admin")
    public ResponseEntity<?> createAdmin(@Valid @RequestBody User user, BindingResult result) {
        checkErrors(result);
        user.addRole(User.Role.ROLE_ADMIN);
        userRepo.save(Registration.toUser(encoder, user, true));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}
