package com.example.apikurimas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private double weight;
    private String productName;
    @ManyToOne
    private Destination destination;

    public Cargo(String productName, Double weight) {
        this.productName = productName;
        this.weight = weight;
    }

    public Cargo(Integer id, String productName, Double weight) {
        this.productName = productName;
        this.weight = weight;
        this.id = id;
    }
}
