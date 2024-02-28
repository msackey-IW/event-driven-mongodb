package com.api.eventdrivenuserservice.domain.user.core.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString(exclude = "id")
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class User {


    private String id;

    @NonNull
    private String firstName;

    @NonNull
    private String lastName;

    private int age;

    @NonNull
    private String sex;

}