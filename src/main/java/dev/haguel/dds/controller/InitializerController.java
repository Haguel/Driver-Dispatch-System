package dev.haguel.dds.controller;

import dev.haguel.dds.service.InitializerService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
public class InitializerController {
    private final InitializerService initializerService;

    @GetMapping(EndPoints.GET_INITIALIZER_MENU)
    public String showInitializerPage(Model model) {
        EndPoints.setMainMenuEndpoints(model);
        EndPoints.setInitializerMenuEndpoints(model);

        return "initializer";
    }

    @PostMapping(EndPoints.CLEAR_DB)
    public String clearDatabase(Model model) {
        initializerService.clearDb();

        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("message", "Databased cleared successfully.");

        return "redirect:/initializer";
    }

    @PostMapping(EndPoints.INIT_DRIVERS)
    public String initializeDrivers(Model model) {
        initializerService.initDrivers();

        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("message", "Drivers initialized successfully.");

        return "redirect:/initializer";
    }

    @PostMapping(EndPoints.INIT_VEHICLES)
    public String initializeVehicles(Model model) {
        initializerService.initVehicles();

        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("message", "Vehicles initialized successfully.");

        return "redirect:/initializer";
    }

    @PostMapping(EndPoints.INIT_CARGO_ORDERS)
    public String initializeCargoOrders(Model model) {
        initializerService.initCargoOrders();

        EndPoints.setMainMenuEndpoints(model);
        model.addAttribute("message", "Cargo orders initialized successfully.");

        return "redirect:/initializer";
    }
}
