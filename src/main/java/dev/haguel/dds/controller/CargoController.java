package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.DTO.DestinationDTO;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.service.CargoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cargo")
@RequiredArgsConstructor
public class CargoController {
    private final CargoService cargoService;

    @GetMapping()
    public String listCargoOrders(Model model) {
        List<CargoOrder> orders = cargoService.getOrders();
        model.addAttribute("orders", orders);

        return "cargoOrders";
    }

    @GetMapping("/{id}")
    public String viewCargoOrder(@PathVariable Long id, Model model) {
        try {
            CargoOrder order = cargoService.getOrderById(id);
            model.addAttribute("order", order);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "cargoOrder";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        CargoOrderDTO cargoOrderDTO = new CargoOrderDTO();
        cargoOrderDTO.setDestinationDTO(new DestinationDTO());
        model.addAttribute("cargoOrderDTO", cargoOrderDTO);

        return "createCargoOrder";
    }

    @PostMapping
    public String createCargoOrder(@ModelAttribute CargoOrderDTO cargoOrderDTO) {
        try {
            cargoService.createOrder(cargoOrderDTO);
        } catch (Exception e) {
            return "redirect:/cargo/create?error=" + e.getMessage();
        }

        return "redirect:/cargo/orders";
    }
}
