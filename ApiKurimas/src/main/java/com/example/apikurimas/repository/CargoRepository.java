package com.example.apikurimas.repository;

import com.example.apikurimas.model.Cargo;
import com.example.apikurimas.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {
}
