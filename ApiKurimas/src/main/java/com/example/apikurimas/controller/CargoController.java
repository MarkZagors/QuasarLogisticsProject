package com.example.apikurimas.controller;

import com.example.apikurimas.model.Cargo;
import com.example.apikurimas.repository.CargoRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CargoController {
    @Autowired
    private CargoRepository cargoRepository;

    @JsonIgnore
    @GetMapping("/cargos")
    @CrossOrigin
    public List<Cargo> getAll() {
        return cargoRepository.findAll();
    }

    @PostMapping(path="/cargos/add")
    @CrossOrigin
    public @ResponseBody String addNew (@RequestParam String productName, @RequestParam String weight) {
        Cargo checkpoint = new Cargo(productName, Double.parseDouble(weight));
        cargoRepository.save(checkpoint);
        return "Saved";
    }

    @GetMapping("/cargos/{id}")
    @CrossOrigin
    public ResponseEntity<Cargo> getById(@PathVariable(value = "id") Integer id) {
        Cargo checkpoint = cargoRepository.findById(id).isPresent() ? cargoRepository.findById(id).get() : null;
        return ResponseEntity.ok().body(checkpoint);
    }

    @PutMapping("/cargos/{id}")
    @CrossOrigin
    public @ResponseBody String update(@PathVariable(value = "id") Integer id, @RequestParam String productName, @RequestParam String weight) {
        Cargo checkpoint = new Cargo(id, productName, Double.parseDouble(weight));
        cargoRepository.save(checkpoint);
        return "Saved";
    }

    @DeleteMapping(value = "/cargos/{id}")
    @CrossOrigin
    public @ResponseBody String delete(@PathVariable("id") Integer id) {
        cargoRepository.deleteById(id);
        return "Deleted";
    }
}
