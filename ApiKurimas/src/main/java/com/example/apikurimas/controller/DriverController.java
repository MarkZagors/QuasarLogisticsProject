package com.example.apikurimas.controller;

import com.example.apikurimas.model.Driver;
import com.example.apikurimas.repository.DriverRepository;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DriverController {
    @Autowired
    private DriverRepository driverRepository;

    @JsonIgnore
    @GetMapping("/drivers")
    @CrossOrigin
    public List<Driver> getAll() {
        return driverRepository.findAll();
    }

    @PostMapping(path="/drivers/add")
    @CrossOrigin
    public @ResponseBody String addNewDriver (@RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String telephoneNumber, @RequestParam String dateOfBirth, @RequestParam String driverLicenceID, @RequestParam String medicalCheckID) {
        LocalDate date = LocalDate.parse(dateOfBirth);
        Driver driver = new Driver(username, password, date, email, telephoneNumber, driverLicenceID, medicalCheckID);
        driverRepository.save(driver);
        return "Saved";
    }

    @JsonIgnore
    @GetMapping("/drivers/{id}")
    @CrossOrigin
    public ResponseEntity<Driver> getDriverById(@PathVariable(value = "id") Integer id) {
        Driver driver = driverRepository.findById(id).isPresent() ? driverRepository.findById(id).get() : null;
        return ResponseEntity.ok().body(driver);
    }

    @PutMapping("/drivers/{id}")
    @CrossOrigin
    public @ResponseBody String updateDriver(@PathVariable(value = "id") Integer id, @RequestParam String username, @RequestParam String password, @RequestParam String email, @RequestParam String telephoneNumber, @RequestParam String dateOfBirth, @RequestParam String driverLicenceID, @RequestParam String medicalCheckID) {
        LocalDate date = LocalDate.parse(dateOfBirth);
        Driver driver = new Driver(id, username, password, date, email, telephoneNumber, driverLicenceID, medicalCheckID);
        driverRepository.save(driver);
        return "Saved";
    }

    @DeleteMapping(value = "/drivers/{id}")
    @CrossOrigin
    public @ResponseBody String deleteDriver(@PathVariable("id") Integer id) {
        driverRepository.deleteById(id);
        return "Deleted";
    }
}
