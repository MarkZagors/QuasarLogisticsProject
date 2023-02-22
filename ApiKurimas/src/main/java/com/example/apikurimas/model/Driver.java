package com.example.apikurimas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Driver extends User{
    String driverLicenceID;
    String medicalCheckID;

//    @ManyToMany (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
//    private List<Cargo> cargos;

    @ManyToMany (cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    private List<Destination> destinations;

    public Driver(String username, String password, LocalDate date, String email, String phoneNumber, String driverLicenceID, String medicalCheckID) {
        super(username, password, date, email, phoneNumber);
        this.driverLicenceID = driverLicenceID;
        this.medicalCheckID = medicalCheckID;
        this.destinations = Collections.emptyList();
    }

    public Driver(Integer id, String username, String password, LocalDate date, String email, String phoneNumber, String driverLicenceID, String medicalCheckID) {
        super(username, password, date, email, phoneNumber);
        this.id = id;
        this.driverLicenceID = driverLicenceID;
        this.medicalCheckID = medicalCheckID;
        this.destinations = Collections.emptyList();
    }

    public Driver(Integer id) {
        this.id = id;
    }
}
