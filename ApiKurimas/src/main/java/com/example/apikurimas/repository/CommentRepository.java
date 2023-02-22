package com.example.apikurimas.repository;

import com.example.apikurimas.model.Cargo;
import com.example.apikurimas.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
