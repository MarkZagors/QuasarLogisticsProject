package com.example.apikurimas.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Forum {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;

    @OneToMany
    List<Comment> comments;

    public Forum(String title, String description) {
        this.title = title;
        this.description = description;
        this.comments = Collections.emptyList();
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }
}
