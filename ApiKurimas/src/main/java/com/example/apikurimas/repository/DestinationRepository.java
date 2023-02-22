package com.example.apikurimas.repository;

import com.example.apikurimas.model.Destination;
import com.example.apikurimas.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Integer> {
}
