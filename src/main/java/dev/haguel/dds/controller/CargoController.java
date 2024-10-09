package dev.haguel.dds.controller;

import dev.haguel.dds.DTO.CargoOrderDTO;
import dev.haguel.dds.DTO.DestinationDTO;
import dev.haguel.dds.model.CargoOrder;
import dev.haguel.dds.service.CargoOrderService;
import dev.haguel.dds.util.EndPoints;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class CargoController {
    private final CargoOrderService cargoOrderService;

    @GetMapping(value = EndPoints.GET_CARGO_ORDERS)
    public String listCargoOrders(Model model) {
        List<CargoOrder> orders = cargoOrderService.getOrders();

        EndPoints.setMenuEndpoints(model);
        model.addAttribute("orders", orders);
        model.addAttribute("getCargoOrderEndpoint", EndPoints.GET_CARGO_ORDER);

        return "cargoOrders";
    }

    @GetMapping(value = EndPoints.GET_CARGO_ORDER)
    public String viewCargoOrder(@PathVariable Long id, Model model) {
        EndPoints.setMenuEndpoints(model);
        try {
            CargoOrder order = cargoOrderService.getOrderById(id);
            model.addAttribute("order", order);
            model.addAttribute("getCargoOrdersEndpoint", EndPoints.GET_CARGO_ORDERS);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }

        return "cargoOrder";
    }

    @GetMapping(value = EndPoints.CREATE_CARGO_ORDER_FORM)
    public String createCargoOrderForm(Model model) {
        CargoOrderDTO cargoOrderDTO = new CargoOrderDTO();
        cargoOrderDTO.setDestinationDTO(new DestinationDTO());

        EndPoints.setMenuEndpoints(model);
        model.addAttribute("cargoOrderDTO", cargoOrderDTO);
        model.addAttribute("createOrderEndpoint", EndPoints.CREATE_CARGO_ORDER);

        return "createCargoOrder";
    }

    @GetMapping(value = EndPoints.CREATE_CARGO_ORDER)
    public String createCargoOrder(@ModelAttribute @Valid CargoOrderDTO cargoOrderDTO) {
        try {
            CargoOrder cargoOrder = cargoOrderService.createOrder(cargoOrderDTO);

            return "redirect:" + EndPoints.GET_CARGO_ORDER.replace("{id}", String.valueOf(cargoOrder.getId()));
        } catch (Exception e) {
            return "error";
        }
    }
}
