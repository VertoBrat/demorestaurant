package ru.photorex.demorestaurant.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
@Component
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(name = "dish_name")
    private String name;

    @DecimalMin("1")
    private Long price;

    @Column(name = "created_at")
    private LocalDate createdAt;

    @ManyToOne(targetEntity = Restaurant.class,fetch = FetchType.EAGER)
    private Restaurant restaurant;

    @PrePersist
    private void init() {
        this.createdAt = LocalDate.now();
    }
}
