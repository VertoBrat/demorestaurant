package ru.photorex.demorestaurant.web;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.service.UsersService;
import ru.photorex.demorestaurant.to.UserAssembler;
import ru.photorex.demorestaurant.to.UserTo;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private UsersService usersService;
    private UserAssembler userAssembler;

    public UsersController(UsersService usersService, UserAssembler userAssembler) {
        this.usersService = usersService;
        this.userAssembler = userAssembler;
    }

    public ResponseEntity<?> getAllPaged(Pageable pageable, PagedResourcesAssembler<User> assembler) {
        PagedResources<UserTo> resources = assembler.toResource(usersService.getPaged(pageable), userAssembler);
        return new ResponseEntity<>(resources, HttpStatus.OK);
    }
}
