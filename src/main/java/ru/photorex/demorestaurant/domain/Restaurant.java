package ru.photorex.demorestaurant.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Data
@Entity
@NoArgsConstructor
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Must be NOT NULL")
    private String name;

    @NotNull(message = "Must be NOT NULL")
    private String location;

    @Column(name = "updated_at")
    private LocalDate updatedAt;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.PERSIST)
    private List<Dish> dishes;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "restaurant")
    private Set<Vote> votes;

    @PrePersist
    public void init() {
        this.updatedAt = LocalDate.now();
    }

    public Restaurant(Integer id) {
        this.id = id.longValue();
    }
}
