package ru.photorex.demorestaurant.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.excp.UserNotFoundException;
import ru.photorex.demorestaurant.repo.UserRepo;
import ru.photorex.demorestaurant.to.UserAssembler;
import ru.photorex.demorestaurant.to.UserTo;
import ru.photorex.demorestaurant.web.UsersController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;


@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class UsersService {

    private UserRepo userRepo;
    private UserAssembler userAssembler;

    public Page<User> getPaged(Pageable pageable) {
        return userRepo.getPaged(pageable);
    }

    public Resource<UserTo> getById(Long id) {
        User user = userRepo.findById(id).orElseThrow(()->new UserNotFoundException(id));
        UserTo userTo = userAssembler.toResource(user);
        return new Resource<>(userTo, linkTo(methodOn(UsersController.class).getById(id)).withSelfRel());
    }
}
