package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.DriverDTO;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.model.Driver;
import dev.haguel.dds.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/drivers")
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping()
    public String createDriver(@ModelAttribute DriverDTO driverDTO) {
        Driver driver = driverService.createDriver(driverDTO);

        return "redirect:/drivers/" + driver.getId();
    }

    @GetMapping()
    public String getAllDrivers(Model model) {
        model.addAttribute("drivers", driverService.getDrivers());

        return "drivers";
    }

    @GetMapping("/{id}")
    public String getDriverById(@PathVariable("id") Long id, Model model) {
        try {
            Driver driver = driverService.getDriversById(id);
            model.addAttribute("driver", driver);

            return "driver";
        } catch (DriverNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());

            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateDriverForm(Model model) {
        model.addAttribute("driverDTO", new DriverDTO());
        return "createDriver";
    }
}