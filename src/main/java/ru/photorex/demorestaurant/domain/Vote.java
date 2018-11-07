package ru.photorex.demorestaurant.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@NoArgsConstructor
public class Vote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int rang;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(targetEntity = Restaurant.class)
    private Restaurant restaurant;

    @ManyToOne(targetEntity = User.class)
    private User user;

    @PrePersist
    public void init() {
        this.createdAt = LocalDateTime.now();
    }
}
