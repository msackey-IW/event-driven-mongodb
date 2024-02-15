package com.api.eventdrivenuserservice.domain.user.core.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "id")
@Entity
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NonNull
    @Column(name = "firstName")
    private String firstName;

    @NonNull
    @Column(name = "lastName")
    private String lastName;

    @Column(name = "age")
    private int age;

    @NonNull
    @Column(name = "sex")
    private String sex;
}