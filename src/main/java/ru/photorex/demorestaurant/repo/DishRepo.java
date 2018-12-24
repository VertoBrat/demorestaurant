package ru.photorex.demorestaurant.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DishRepo extends JpaRepository<Dish, Long> {
    List<Dish> findByRestaurantAndCreatedAt(Restaurant restaurant, LocalDate date);
}
