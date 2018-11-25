package ru.photorex.demorestaurant.repo;

import ch.qos.logback.classic.pattern.LineOfCallerConverter;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.photorex.demorestaurant.domain.Restaurant;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant, Long> {

  //  @Query(value = "select distinct r from Restaurant r where r.id in (select d.restaurant.id from Dish d where d.createdAt=?1)")
    @Cacheable("restaurant")
  //@EntityGraph(value = "Restaurant.dishes")
    @Query("select distinct r from Restaurant r join fetch r.dishes d where d.createdAt=?1")
    List<Restaurant> getByDay(LocalDate date);

    @Cacheable("pagingRest")
    @Query(value = "select distinct r from Restaurant r join fetch r.dishes d where d.createdAt=?1",
    countQuery = "select count (distinct r) from Restaurant r join r.dishes d where d.createdAt=?1")
    Page<Restaurant> getPaged(LocalDate date, Pageable pageable);

    List<Restaurant> findByUpdatedAt(LocalDate date);

    Optional<Restaurant> findByIdAndUpdatedAt(Long id, LocalDate date);

    @Query("select distinct r from Restaurant r join fetch Dish d on d.restaurant.id=r.id join fetch Vote v on v.restaurant.id=r.id")
    List<Restaurant> all();

//    @Query(nativeQuery = true, value =
//            "SELECT r.*, d.* FROM restaurant r INNER JOIN dish d ON r.id = d.restaurant_id AND d.created_at=?1")
//  //  @Query("select r from Restaurant r where r.updatedAt=?1")
////    @Query(nativeQuery = true,
////            value = "SELECT DISTINCT * FROM restaurant r" +
////                    " INNER JOIN dish d ON r.id = d.restaurant_id" +
////                    " WHERE r.updated_at=?1 AND d.created_at=?1")
//    List<Restaurant> find(LocalDate date);


}
