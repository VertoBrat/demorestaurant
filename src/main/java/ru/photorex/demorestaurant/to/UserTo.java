package ru.photorex.demorestaurant.to;

import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;
import ru.photorex.demorestaurant.domain.User;

import java.util.Set;

@Relation(collectionRelation = "users")
public class UserTo extends ResourceSupport {

    @Getter
    private String name;

    @Getter
    private String email;

    @Getter
    private Set<User.Role> roles;

    public UserTo(User user) {
        this.name = user.getUserName();
        this.email = user.getEmail();
        this.roles = user.getRoles();
    }
}
