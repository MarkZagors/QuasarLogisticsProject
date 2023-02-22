package com.example.apikurimas.controller;

import com.example.apikurimas.model.Destination;
import com.example.apikurimas.repository.DestinationRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
public class DestinationController {
    @Autowired
    private DestinationRepository destinationRepository;

    @JsonIgnore
    @GetMapping("/destinations")
    @CrossOrigin
    public List<Destination> getAll() {
        return destinationRepository.findAll();
    }

    @PostMapping(path="/destinations/add")
    @CrossOrigin
    public @ResponseBody String addNew (@RequestParam String name, @RequestParam String arriveDate, @RequestParam String departDate) {
        Destination checkpoint = new Destination(name, LocalDate.parse(arriveDate), LocalDate.parse(departDate));
        destinationRepository.save(checkpoint);
        return "Saved";
    }

    @GetMapping("/destinations/{id}")
    @CrossOrigin
    public ResponseEntity<Destination> getById(@PathVariable(value = "id") Integer id) {
        Destination checkpoint = destinationRepository.findById(id).isPresent() ? destinationRepository.findById(id).get() : null;
        return ResponseEntity.ok().body(checkpoint);
    }

    @PutMapping("/destinations/{id}")
    @CrossOrigin
    public @ResponseBody String update(@PathVariable(value = "id") Integer id, @RequestParam String name, @RequestParam String arriveDate, @RequestParam String departDate) {
        Destination checkpoint = new Destination(id, name, LocalDate.parse(arriveDate), LocalDate.parse(departDate));
        destinationRepository.save(checkpoint);
        return "Saved";
    }

    @PutMapping("/destinations/assign/{id}")
    @CrossOrigin
    public @ResponseBody String updateDriver(@PathVariable(value = "id") Integer id, @RequestParam int driverID) {
        Destination destination = destinationRepository.findById(id).isPresent() ? destinationRepository.findById(id).get() : null;
        destination.setDriverID(driverID);
        destinationRepository.save(destination);
        return "Saved";
    }

    @DeleteMapping(value = "/destinations/{id}")
    @CrossOrigin
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
        destinationRepository.deleteById(id);
        return "Deleted";
    }
}
