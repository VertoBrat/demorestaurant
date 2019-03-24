package ru.photorex.demorestaurant.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.repo.UserRepo;
import ru.photorex.demorestaurant.to.UserTo;


@Service
@Transactional(readOnly = true)
public class UsersService {

    private UserRepo userRepo;

    public UsersService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public Page<User> getPaged(Pageable pageable) {
        return userRepo.getPaged(pageable);
    }
}
