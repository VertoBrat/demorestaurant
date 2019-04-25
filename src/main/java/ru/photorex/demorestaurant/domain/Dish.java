package ru.photorex.demorestaurant.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@BatchSize(size = 5)
@NoArgsConstructor
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    @PrePersist
    private void init() {
        this.createdAt = LocalDate.now();
    }
}
