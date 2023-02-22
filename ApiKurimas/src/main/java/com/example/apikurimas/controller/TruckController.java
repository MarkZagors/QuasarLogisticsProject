package com.example.apikurimas.controller;

import com.example.apikurimas.model.Truck;
import com.example.apikurimas.repository.ManagerRepository;
import com.example.apikurimas.repository.TruckRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class TruckController {
    @Autowired
    private TruckRepository truckRepository;

    @JsonIgnore
    @GetMapping("/trucks")
    @CrossOrigin
    public List<Truck> getAll() {
        return truckRepository.findAll();
    }

    @PostMapping(path="/trucks/add")
    @CrossOrigin
    public @ResponseBody String addNew (@RequestParam String model, @RequestParam String maxSpeed) {
        Truck truck = new Truck(model, Double.parseDouble(maxSpeed));
        truckRepository.save(truck);
        return "Saved";
    }

    @GetMapping("/trucks/{id}")
    @CrossOrigin
    public ResponseEntity<Truck> getById(@PathVariable(value = "id") Integer id) {
        Truck truck = truckRepository.findById(id).isPresent() ? truckRepository.findById(id).get() : null;
        return ResponseEntity.ok().body(truck);
    }

    @PutMapping("/trucks/{id}")
    @CrossOrigin
    public @ResponseBody String update(@PathVariable(value = "id") Integer id, @RequestParam String model, @RequestParam String maxSpeed) {
        Truck truck = new Truck(id, model, Double.parseDouble(maxSpeed));
        truckRepository.save(truck);
        return "Saved";
    }

    @DeleteMapping(value = "/trucks/{id}")
    @CrossOrigin
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
        truckRepository.deleteById(id);
        return "Deleted";
    }
}
