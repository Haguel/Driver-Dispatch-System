package dev.haguel.dds.controller;

import dev.haguel.dds.service.InitializerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/initializer")
@RequiredArgsConstructor
public class InitializerController {
    private final InitializerService initializerService;

    @GetMapping
    public String showInitializerPage(Model model) {
        return "initializer";
    }

    @PostMapping("/clearDb")
    public String clearDatabase(Model model) {
        initializerService.clearDb();
        model.addAttribute("message", "Databased cleared successfully.");

        return "initializer";
    }

    @PostMapping("/initDrivers")
    public String initializeDrivers(Model model) {
        initializerService.initDrivers();
        model.addAttribute("message", "Drivers initialized successfully.");

        return "initializer";
    }

    @PostMapping("/initVehicles")
    public String initializeVehicles(Model model) {
        initializerService.initVehicles();
        model.addAttribute("message", "Vehicles initialized successfully.");

        return "initializer";
    }
}
