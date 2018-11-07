package ru.photorex.demorestaurant.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "user_name")
    private String userName;

    @NotBlank
    @Size(min = 5, max = 100)
    private String password;

    @Email
    @NotBlank
    @Size(max = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    private Set<Role> roles;

    public User() {
    }

    public User(String userName, String encode, String email, HashSet<Role> objects) {
        this.userName = userName;
        this.password = encode;
        this.email = email;
        this.roles = objects;
    }

    @PrePersist
    private void initRole() {
        roles.add(Role.ROLE_USER);
    }
}
