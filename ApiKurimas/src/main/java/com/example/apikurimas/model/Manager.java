package com.example.apikurimas.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
//@AllArgsConstructor
@Entity
public class Manager extends User{
    String managerDocument;

    public Manager(String username, String password, LocalDate date, String email, String phoneNumber, String managerDocument) {
        super(username, password, date, email, phoneNumber);
        this.managerDocument = managerDocument;
    }

    public Manager(Integer id, String username, String password, LocalDate date, String email, String phoneNumber, String managerDocument) {
        super(username, password, date, email, phoneNumber);
        this.managerDocument = managerDocument;
        this.id = id;
    }
}
