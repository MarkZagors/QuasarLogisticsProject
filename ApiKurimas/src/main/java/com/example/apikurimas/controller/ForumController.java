package com.example.apikurimas.controller;

import com.example.apikurimas.model.Destination;
import com.example.apikurimas.model.Forum;
import com.example.apikurimas.repository.DestinationRepository;
import com.example.apikurimas.repository.ForumRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class ForumController {
    @Autowired
    private ForumRepository forumRepository;

    @JsonIgnore
    @GetMapping("/forums")
    @CrossOrigin
    public List<Forum> getAll() {
        return forumRepository.findAll();
    }

    @PostMapping(path="/forums/add")
    @CrossOrigin
    public @ResponseBody String addNew (@RequestParam String title, @RequestParam String description) {
        Forum forum = new Forum(title, description);
        forumRepository.save(forum);
        return "Saved";
    }

    @GetMapping("/forums/{id}")
    @CrossOrigin
    public ResponseEntity<Forum> getById(@PathVariable(value = "id") Integer id) {
        Forum forum = forumRepository.findById(id).isPresent() ? forumRepository.findById(id).get() : null;
        return ResponseEntity.ok().body(forum);
    }

    @PutMapping("/forums/{id}")
    @CrossOrigin
    public @ResponseBody String update(@PathVariable(value = "id") Integer id, @RequestParam String title, @RequestParam String description) {
        Forum forum = new Forum(title, description);
        forumRepository.save(forum);
        return "Saved";
    }

    @DeleteMapping(value = "/forums/{id}")
    @CrossOrigin
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
        forumRepository.deleteById(id);
        return "Deleted";
    }
}
