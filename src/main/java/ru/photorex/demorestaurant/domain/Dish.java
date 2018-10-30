package ru.photorex.demorestaurant.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Component
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dish_name")
    private String name;

    private Long price;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne
    private Restaurant restaurant;
}
