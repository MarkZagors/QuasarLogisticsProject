package com.example.apikurimas.repository;

import com.example.apikurimas.model.Driver;
import com.example.apikurimas.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriverRepository extends JpaRepository<Driver, Integer> {
}
