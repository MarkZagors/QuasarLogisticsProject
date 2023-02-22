package com.example.apikurimas.repository;

import com.example.apikurimas.model.Manager;
import com.example.apikurimas.model.Truck;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TruckRepository extends JpaRepository<Truck, Integer> {
}
