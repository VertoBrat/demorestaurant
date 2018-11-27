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

    @Cacheable("pagingRest")
    @Query(value = "select distinct r from Restaurant r join fetch r.dishes d where d.createdAt=?1",
            countQuery = "select count (distinct r) from Restaurant r join r.dishes d where d.createdAt=?1")
    Page<Restaurant> getPaged(LocalDate date, Pageable pageable);

    Optional<Restaurant> findByIdAndUpdatedAt(Long id, LocalDate date);

    @Query(value = "select distinct r from Restaurant r",
    countQuery = "select count (distinct r) from Restaurant r")
    Page<Restaurant> all(Pageable pageable);
}
