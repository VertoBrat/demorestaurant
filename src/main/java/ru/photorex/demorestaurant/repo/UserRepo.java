package ru.photorex.demorestaurant.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.User;

import java.util.Optional;

@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    Optional<User> getByUserName(String userName);
}
