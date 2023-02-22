package com.example.apikurimas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Getter
@Setter
//@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Destination {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private String name;

    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Checkpoint> checkpoints;
    LocalDate arriveDate;
    LocalDate departDate;
    @ManyToOne
    Manager creator;
    @ManyToMany (cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    List<Manager> assignees;
    int driverID = -1;
    @ManyToOne
    Truck truck;
    boolean inProgress;

    public Destination() {
        this.checkpoints = Collections.emptyList();
    }

    public Destination(String name, LocalDate arriveDate, LocalDate departDate) {
        this.name = name;
        this.arriveDate = arriveDate;
        this.departDate = departDate;
    }

    public Destination(Integer id, String name, LocalDate arriveDate, LocalDate departDate) {
        this.name = name;
        this.arriveDate = arriveDate;
        this.departDate = departDate;
        this.id = id;
    }
}
