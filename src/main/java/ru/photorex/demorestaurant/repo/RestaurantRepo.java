package ru.photorex.demorestaurant.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepo extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findByUpdatedAt(LocalDate date);
    Restaurant findByIdAndUpdatedAt(Long id, LocalDate date);
}
