package ru.photorex.demorestaurant.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> getByUserName(String userName);

    @Query(value = "select u from User u join fetch u.roles",
            countQuery = "select count(u) from User u")
    Page<User> getPaged(Pageable pageable);
}
