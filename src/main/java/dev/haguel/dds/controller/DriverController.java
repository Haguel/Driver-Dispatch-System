package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.DriverDTO;
import dev.haguel.dds.exception.DriverNotFoundException;
import dev.haguel.dds.model.Driver;
import dev.haguel.dds.service.DriverService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
public class DriverController {
    private final DriverService driverService;

    @PostMapping(EndPoints.CREATE_DRIVER)
    public String createDriver(@ModelAttribute @Valid DriverDTO driverDTO) {
        Driver driver = driverService.createDriver(driverDTO);

        return "redirect:" + EndPoints.GET_DRIVER.replace("{id}", String.valueOf(driver.getId()));
    }

    @GetMapping(EndPoints.GET_DRIVERS)
    public String getAllDrivers(Model model) {
        EndPoints.setMenuEndpoints(model);
        model.addAttribute("drivers", driverService.getDrivers());
        model.addAttribute("getDriverEndpoint", EndPoints.GET_DRIVER);

        return "drivers";
    }

    @GetMapping(EndPoints.GET_DRIVER)
    public String getDriverById(@PathVariable("id") Long id, Model model) {
        EndPoints.setMenuEndpoints(model);
        try {
            Driver driver = driverService.getDriversById(id);

            model.addAttribute("driver", driver);
            model.addAttribute("getDriversEndpoint", EndPoints.GET_DRIVERS);

            return "driver";
        } catch (DriverNotFoundException exception) {
            model.addAttribute("error", exception.getMessage());

            return "error";
        }
    }

    @GetMapping(EndPoints.CREATE_DRIVER_FORM)
    public String createDriverForm(Model model) {
        EndPoints.setMenuEndpoints(model);
        model.addAttribute("driverDTO", new DriverDTO());
        model.addAttribute("createDriverEndpoint", EndPoints.CREATE_DRIVER);

        return "createDriver";
    }
}