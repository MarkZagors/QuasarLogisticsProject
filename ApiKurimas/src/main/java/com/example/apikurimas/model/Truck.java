package com.example.apikurimas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Truck {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    String model;
    double maxSpeed;
    @ManyToOne
    Manager registrar;
    @ManyToMany (fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @Fetch(value = FetchMode.SUBSELECT)
    List<Cargo> cargos;
//    @ManyToOne
//    Driver driver;

    public Truck() {
        this.cargos = Collections.<Cargo>emptyList();
    }

    public Truck(String model, Double maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
    }

    public Truck(Integer id, String model, Double maxSpeed) {
        this.model = model;
        this.maxSpeed = maxSpeed;
        this.id = id;
    }
}
