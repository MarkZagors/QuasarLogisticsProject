package com.example.apikurimas.controller;

import com.example.apikurimas.LoginCredentials;
import com.example.apikurimas.model.Driver;
import com.example.apikurimas.model.Manager;
import com.example.apikurimas.model.Truck;
import com.example.apikurimas.repository.DriverRepository;
import com.example.apikurimas.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LoginController {
    @Autowired
    private DriverRepository driverRepository;
    @Autowired
    private ManagerRepository managerRepository;

    @PostMapping("/login")
    @CrossOrigin
    public String login(@RequestParam String username, @RequestParam String password) {
        List<Driver> drivers = driverRepository.findAll();
        List<Manager> managers = managerRepository.findAll();
        for (Driver driver : drivers) {
            if (driver.getUsername().equals(username) && driver.getPassword().equals(password)) {
                LoginCredentials credentials = new LoginCredentials(true, driver.getId(), "driver", driver.isAdmin());
                return credentials.getCredentials();
            }
        }
        for (Manager manager : managers) {
            if (manager.getUsername().equals(username) && manager.getPassword().equals(password)) {
                LoginCredentials credentials = new LoginCredentials(true, manager.getId(), "manager", manager.isAdmin());
                return credentials.getCredentials();
            }
        }
        LoginCredentials failedCredentials = new LoginCredentials(false, -1, "null", false);
        return failedCredentials.getCredentials();
    }
}
