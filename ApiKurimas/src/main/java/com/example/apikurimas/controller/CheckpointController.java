package com.example.apikurimas.controller;

import com.example.apikurimas.model.Checkpoint;
import com.example.apikurimas.repository.CheckpointRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CheckpointController {
    @Autowired
    private CheckpointRepository checkpointRepository;

    @JsonIgnore
    @GetMapping("/checkpoints")
    @CrossOrigin
    public List<Checkpoint> getAll() {
        return checkpointRepository.findAll();
    }

    @PostMapping(path="/checkpoints/add")
    @CrossOrigin
    public @ResponseBody String addNew (@RequestParam String name, @RequestParam String coordinatesLongitude, @RequestParam String coordinatesLatitude) {
        Checkpoint checkpoint = new Checkpoint(name, Double.parseDouble(coordinatesLongitude), Double.parseDouble(coordinatesLatitude));
        checkpointRepository.save(checkpoint);
        return "Saved";
    }

    @GetMapping("/checkpoints/{id}")
    @CrossOrigin
    public ResponseEntity<Checkpoint> getById(@PathVariable(value = "id") Integer id) {
        Checkpoint checkpoint = checkpointRepository.findById(id).isPresent() ? checkpointRepository.findById(id).get() : null;
        return ResponseEntity.ok().body(checkpoint);
    }

    @PutMapping("/checkpoints/{id}")
    @CrossOrigin
    public @ResponseBody String update(@PathVariable(value = "id") Integer id, @RequestParam String name, @RequestParam String coordinatesLongitude, @RequestParam String coordinatesLatitude) {
        Checkpoint checkpoint = new Checkpoint(id, name, Double.parseDouble(coordinatesLongitude), Double.parseDouble(coordinatesLatitude));
        checkpointRepository.save(checkpoint);
        return "Saved";
    }

    @DeleteMapping(value = "/checkpoints/{id}")
    @CrossOrigin
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
        checkpointRepository.deleteById(id);
        return "Deleted";
    }
}
