package ru.photorex.demorestaurant.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Component
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String location;

    @NotNull
    @Column(name = "updated_at")
    private LocalDate updatedAt;

   @OneToMany(fetch = FetchType.LAZY,
              cascade = CascadeType.ALL)
   @JoinColumn(name = "restaurant_id")
    private List<Dish> dishes;
}
