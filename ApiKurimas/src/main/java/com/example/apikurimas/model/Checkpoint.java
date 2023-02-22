package com.example.apikurimas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Checkpoint {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private Double coordinatesLongitude;
    private Double coordinatesLatitude;
    @ManyToOne
    Driver driver;
    LocalDate arriveDate;

    @ManyToOne
    private Manager creator;

    public Checkpoint(String name, Double coordinatesLongitude, Double coordinatesLatitude) {
        this.name = name;
        this.coordinatesLongitude = coordinatesLongitude;
        this.coordinatesLatitude = coordinatesLatitude;
    }

    public Checkpoint(Integer id, String name, Double coordinatesLongitude, Double coordinatesLatitude) {
        this.name = name;
        this.coordinatesLongitude = coordinatesLongitude;
        this.coordinatesLatitude = coordinatesLatitude;
        this.id = id;
    }
}
