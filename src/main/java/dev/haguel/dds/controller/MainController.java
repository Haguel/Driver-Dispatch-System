package dev.haguel.dds.controller;

import dev.haguel.dds.util.EndPoints;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping(EndPoints.MAIN)
    public String getMenu(Model model) {
        EndPoints.setMenuEndpoints(model);

        return "/login";
    }
}
