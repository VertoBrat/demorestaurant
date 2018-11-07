package ru.photorex.demorestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.Restaurant;
import ru.photorex.demorestaurant.domain.User;
import ru.photorex.demorestaurant.domain.Vote;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface VoteRepo extends JpaRepository<Vote, Long> {

    Vote findByUserIdAndCreatedAtBetween(long userId, LocalDateTime start, LocalDateTime end);
    List<Vote> findByRestaurantAndCreatedAtBetween(Restaurant restaurant, LocalDateTime start, LocalDateTime end);
}
