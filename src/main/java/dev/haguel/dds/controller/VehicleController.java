package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.Vehicle;
import dev.haguel.dds.service.VehicleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/vehicles")
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping()
    public String getAllVehicles(Model model) {
        model.addAttribute("vehicles", vehicleService.getVehicles());
        return "vehicles";
    }

    @GetMapping("/{id}")
    public String getVehicleById(@PathVariable("id") Long id, Model model) {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            model.addAttribute("vehicle", vehicle);
            return "vehicle";
        } catch (VehicleNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }

    @GetMapping("/create")
    public String showCreateVehicleForm(Model model) {
        model.addAttribute("vehicleDTO", new VehicleDTO());
        return "createVehicle";
    }

    @PostMapping()
    public String createVehicle(@ModelAttribute @Valid VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleService.createVehicle(vehicleDTO);
        return "redirect:/vehicles/" + vehicle.getId();
    }

    @PostMapping("/{id}/broken-status")
    public String handleBrokenStatus(@PathVariable("id") Long id, Model model) {
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            if (vehicle.isBroken()) {
                vehicleService.resetBreakStatus(vehicle);
            } else {
                vehicleService.setBrokenStatus(vehicle);
            }
            return "redirect:/vehicles/" + id;
        } catch (VehicleNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());
            return "error";
        }
    }
}