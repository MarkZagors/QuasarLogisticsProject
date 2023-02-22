package com.example.apikurimas.repository;

import com.example.apikurimas.model.Driver;
import com.example.apikurimas.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {
}
