package ru.photorex.demorestaurant.repo;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RestaurantRepo extends CrudRepository<Restaurant, Long> {
    List<Restaurant> findByUpdatedAt(LocalDate date);
    Restaurant findByIdAndUpdatedAt(Long id, LocalDate date);

    @Query(nativeQuery = true, value =
            "SELECT r.*, d.* FROM restaurant r INNER JOIN dish d ON r.id = d.restaurant_id AND d.created_at=?1")
  //  @Query("select r from Restaurant r where r.updatedAt=?1")
//    @Query(nativeQuery = true,
//            value = "SELECT DISTINCT * FROM restaurant r" +
//                    " INNER JOIN dish d ON r.id = d.restaurant_id" +
//                    " WHERE r.updated_at=?1 AND d.created_at=?1")
    List<Restaurant> find(LocalDate date);


}
