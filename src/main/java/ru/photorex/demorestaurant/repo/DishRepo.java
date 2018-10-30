package ru.photorex.demorestaurant.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.Dish;
import ru.photorex.demorestaurant.domain.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DishRepo extends CrudRepository<Dish, Long> {
    List<Dish> findByRestaurantAndCreatedAt(Restaurant restaurant, LocalDate date);
    List<Dish> findByRestaurant(Restaurant restaurant);
}
