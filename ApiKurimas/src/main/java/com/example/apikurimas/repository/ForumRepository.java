package com.example.apikurimas.repository;

import com.example.apikurimas.model.Forum;
import com.example.apikurimas.model.Manager;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ForumRepository extends JpaRepository<Forum, Integer> {
}
