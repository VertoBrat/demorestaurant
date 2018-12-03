package ru.photorex.demorestaurant.domain;

import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;
import ru.photorex.demorestaurant.util.PostgreSQLEnumType;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users")
@TypeDef(name = "enum", typeClass = PostgreSQLEnumType.class)
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
    @Type(type = "enum")
    private Set<Role> roles = new HashSet<>();

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "user")
    private List<Vote> votes;

    public User() {
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(String userName, String encode, String email, Set<Role> objects) {
        this.userName = userName;
        this.password = encode;
        this.email = email;
        this.roles = objects;
    }

    @PrePersist
    private void initRole() {
        roles.add(Role.ROLE_USER);
    }

    public void addRole(Role role) {
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public List<Vote> getVotes() {
        return votes;
    }

    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }
}
