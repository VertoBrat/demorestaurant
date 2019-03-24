package ru.photorex.demorestaurant.to;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.web.UsersController;

@Component
public class UserAssembler extends ResourceAssemblerSupport<User, UserTo> {

    public UserAssembler() {
        super(UsersController.class, UserTo.class);
    }

    @Override
    protected UserTo instantiateResource(User entity) {
        return new UserTo(entity);
    }

    @Override
    public UserTo toResource(User entity) {
        return new UserTo(entity);
    }
}
