package com.example.apikurimas.model;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDate;

@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    @NonNull
    String username;
    @NonNull
    String password;
    @NonNull
    LocalDate dateOfBirth;
    @NonNull
    String email;
    @NonNull
    String telephoneNumber;
    boolean isAdmin;
}
