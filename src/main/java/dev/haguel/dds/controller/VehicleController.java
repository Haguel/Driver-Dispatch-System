package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.VehicleDTO;
import dev.haguel.dds.exception.VehicleNotFoundException;
import dev.haguel.dds.model.Vehicle;
import dev.haguel.dds.service.VehicleService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class VehicleController {
    private final VehicleService vehicleService;

    @GetMapping(EndPoints.GET_VEHICLES)
    public String getAllVehicles(Model model) {
        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("vehicles", vehicleService.getVehicles());
        model.addAttribute("getVehicleEndpoint", EndPoints.GET_VEHICLE);
        model.addAttribute("getBrokenStatusHandlerEndpoint", EndPoints.HANDLE_BROKEN_STATUS);

        return "vehicles";
    }

    @GetMapping(EndPoints.GET_VEHICLE)
    public String getVehicleById(@PathVariable("id") Long id, Model model) {
        EndPoints.setMainMenuEndpoints(model);

        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);

            model.addAttribute("vehicle", vehicle);
            model.addAttribute("getVehiclesEndpoint", EndPoints.GET_VEHICLES);

            return "vehicle";
        } catch (VehicleNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());

            return "error";
        }
    }

    @GetMapping(EndPoints.CREATE_VEHICLE_FORM)
    public String createVehicleForm(Model model) {
        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("vehicleDTO", new VehicleDTO());
        model.addAttribute("createVehicleEndpoint", EndPoints.CREATE_VEHICLE);

        return "createVehicle";
    }

    @PostMapping(EndPoints.CREATE_VEHICLE)
    public String createVehicle(@ModelAttribute @Valid VehicleDTO vehicleDTO) {
        Vehicle vehicle = vehicleService.createVehicle(vehicleDTO);

        return "redirect:" + EndPoints.GET_VEHICLE.replace("{id}", String.valueOf(vehicle.getId()));
    }

    @PostMapping(EndPoints.HANDLE_BROKEN_STATUS)
    public String handleBrokenStatus(@PathVariable("id") Long id, Model model) {
        EndPoints.setMainMenuEndpoints(model);
        try {
            Vehicle vehicle = vehicleService.getVehicleById(id);
            if (vehicle.isBroken()) {
                vehicleService.resetBreakStatus(vehicle);
            } else {
                vehicleService.setBrokenStatus(vehicle);
            }

            return "redirect:" + EndPoints.GET_VEHICLES;
        } catch (VehicleNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());

            return "error";
        }
    }
}