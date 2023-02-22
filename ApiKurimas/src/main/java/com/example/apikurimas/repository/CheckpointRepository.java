package com.example.apikurimas.repository;

import com.example.apikurimas.model.Checkpoint;
import com.example.apikurimas.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckpointRepository extends JpaRepository<Checkpoint, Integer> {
}
