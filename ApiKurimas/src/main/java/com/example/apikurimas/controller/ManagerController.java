package com.example.apikurimas.controller;

import com.example.apikurimas.model.Driver;
import com.example.apikurimas.model.Manager;
import com.example.apikurimas.repository.DriverRepository;
import com.example.apikurimas.repository.ManagerRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class ManagerController {
    @Autowired
    private ManagerRepository managerRepository;

    @JsonIgnore
    @GetMapping("/managers")
    @CrossOrigin
    public List<Manager> getAll() {
        return managerRepository.findAll();
    }

    @PostMapping(path="/managers/add")
    @CrossOrigin
    public @ResponseBody String addNewManager (@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String telephoneNumber, @RequestParam String dateOfBirth, @RequestParam String managerDocument) {
        LocalDate date = LocalDate.parse(dateOfBirth);
        Manager manager = new Manager(username, password, date, email, telephoneNumber, managerDocument);
        managerRepository.save(manager);
        return "Saved";
    }

    @GetMapping("/managers/{id}")
    @CrossOrigin
    public ResponseEntity<Manager> getManagerById(@PathVariable(value = "id") Integer id) {
        Manager manager = managerRepository.findById(id).isPresent() ? managerRepository.findById(id).get() : null;
        return ResponseEntity.ok().body(manager);
    }

    @PutMapping("/managers/{id}")
    @CrossOrigin
    public @ResponseBody String updateManager(@PathVariable(value = "id") Integer id, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String telephoneNumber, @RequestParam String dateOfBirth, @RequestParam String managerDocument) {
        LocalDate date = LocalDate.parse(dateOfBirth);
        Manager manager = new Manager(id, username, password, date, email, telephoneNumber, managerDocument);
        managerRepository.save(manager);
        return "Saved";
    }

    @DeleteMapping(value = "/managers/{id}")
    @CrossOrigin
    public @ResponseBody String deleteManager(@PathVariable("id") Integer id) {
        managerRepository.deleteById(id);
        return "Deleted";
    }
}
