package dev.haguel.dds.controller;

import dev.haguel.dds.util.EndPoints;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    @GetMapping(EndPoints.GET_MAIN_PAGE)
    public String getMainMenu(Model model) {
        EndPoints.setMainMenuEndpoints(model);

        return "homeMenuPage";
    }

    @GetMapping(EndPoints.GET_AUTH_MENU_PAGE)
    public String getAuthMenu(Model model) {
        EndPoints.setAuthMenuEndpoints(model);

        return "authMenuPage";
    }
}
