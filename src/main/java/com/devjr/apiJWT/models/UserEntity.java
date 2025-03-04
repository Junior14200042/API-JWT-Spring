package com.devjr.apiJWT.models;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    private String name;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @ManyToMany(targetEntity = Role.class, fetch = FetchType.EAGER,cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id")
    )
    private Set<Role> roles;
}
